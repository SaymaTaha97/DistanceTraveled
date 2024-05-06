import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class DistanceTraveled {
  public static int distance(int speed, int time) {
    return speed * time;
  }

  public static void saveAsFile(String filename, String report) throws IOException {
    FileWriter writer = new FileWriter(filename);
    writer.write(report);
    writer.close();
  }

  public static String generateReport(String name, String vehicle, int speed, int time) {
    String report = "Report for " + name + " (" + vehicle + "):\n";
    report += "Hour    Distance Traveled\n";
    report += "-----------------------\n";

    for (int hour = 1; hour <= time; hour++) {
      int dist = distance(speed, hour);
      report += String.format("%-8d%d\n", hour, dist);
    }

    return report;
  }

  public static void main(String[] args) throws IOException {
    File file = new File("inputt.txt");
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] data = line.split(",");

      // Check if there are enough fields in the line
      if (data.length != 4) {
        System.out.println("Invalid input line: " + line + ". Skipping...");
        continue;
      }

      String name = data[0];
      String vehicle = data[1];

      if (!isNumeric(data[2]) || !isNumeric(data[3])) {
        System.out.println("Invalid input format: " + line + ". Skipping...");
        continue;
      }

      int speed = Integer.parseInt(data[2]);
      int time = Integer.parseInt(data[3]);

      if (speed < 0 || time < 1) {
        System.out.println("Invalid input for " + name + ". Skipping...");
        continue;
      }

      String report = generateReport(name, vehicle, speed, time);
      String filename = name.toLowerCase() + ".txt";
      saveAsFile(filename, report);
      System.out.println("Report for " + name + " saved as " + filename);
    }
    scanner.close();
  }

  public static boolean isNumeric(String str) {
    Scanner scanner = new Scanner(str);
    boolean result = scanner.hasNextInt();
    scanner.close();
    return result;
  }

}
