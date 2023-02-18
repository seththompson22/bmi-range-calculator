import java.util.Scanner;
public class bmiCalculator {
    public static String underweightRange(double height, String unitType, helper h) {
        String unit = "kg";
        double upperRange = (18.49*height);
        if (unitType.equalsIgnoreCase("i")) {
            upperRange = h.kilogramsToPounds(upperRange);
            unit = "lbs";
        }
        // rounds upperRange to 2 places after the decimal
        upperRange = (double)Math.round(upperRange * 100) / 100;
        // Double.toString(upperRange);
        return "0 " + unit + " - " + upperRange + " " + unit;
    }

    public static String normalRange(double height, String unitType, helper h) {
        String unit = "kg";
        double lowerRange = (18.5*height);
        double upperRange = (24.99*height);
        if (unitType.equalsIgnoreCase("i")) {
            upperRange = h.kilogramsToPounds(upperRange);
            lowerRange = h.kilogramsToPounds(lowerRange);
            unit = "lbs";
        }
        lowerRange = (double)Math.round(lowerRange * 100) / 100;
        upperRange = (double)Math.round(upperRange * 100) / 100;
        return lowerRange + " " + unit + " - " + upperRange + " " + unit;
    }

    public static String overweightRange(double height, String unitType, helper h) {
        String unit = "kg";
        double lowerRange = (25*height);
        double upperRange = (29.99*height);
        if (unitType.equalsIgnoreCase("i")) {
            upperRange = h.kilogramsToPounds(upperRange);
            lowerRange = h.kilogramsToPounds(lowerRange);
            unit = "lbs";
        }
        lowerRange = (double)Math.round(lowerRange * 100) / 100;
        upperRange = (double)Math.round(upperRange * 100) / 100;
        return lowerRange + " " + unit + " - " + upperRange + " " + unit;
    }

    public static String obeseRange(double height, String unitType, helper h) {
        String unit = "kg";
        double lowerRange = (30*height);
        if (unitType.equalsIgnoreCase("i")) {
            lowerRange = h.kilogramsToPounds(lowerRange);
            unit = "lbs";
        }
        lowerRange = (double)Math.round(lowerRange * 100) / 100;
        return lowerRange + " " + unit + "+";
    }

    public static void bmiCalc(double height, double weight) {
        double currentBMI = weight/height;
        String category;
        if (currentBMI < 18.5) {
            category = "Underweight";
        } else if (currentBMI < 25) {
            category = "Normal";
        } else if (currentBMI < 30) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        // underweight is 0-18.49
        // normal is 18.5 to 24.9 inclusive
        // overweight is 25 to 29.99
        // obese is 30+
        // bmi = kg/m^2
        // kg = bmi*m^2

        System.out.println("You are in the "+category+" Range, with a bmi of "+String.format("%.2f", currentBMI)+".");
    }
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        helper h = new helper();
        String welcome = """
        Welcome to the BMI Range Calculator!
        This application will take your height and tell you the weight ranges for each BMI category.""";
        System.out.println(welcome+"\n");
        System.out.println("Will you enter your height in the Metric or Imperial System?");
        System.out.println("(m) Metric");
        System.out.println("(i) Imperial\n");
        double height;
        double weight;

        String unitType = sc.nextLine();
        // collects and converts height and weight to meters^2 and kilograms depending on unit type
        if (unitType.equalsIgnoreCase("m")) {
            System.out.print("Enter your height in centimeters: ");
            height = sc.nextDouble();

            //convert height to meters^2
            height = h.centimetersToMeters(height);
            height = Math.pow(height, 2);

            // collect weight in kilograms
            System.out.print("Enter your weight in kilograms: ");
            weight = sc.nextDouble(); //weight in kilograms

            bmiCalc(height, weight); // prints results to console

            System.out.println("Underweight Range: "+underweightRange(height, unitType, h));
            System.out.println("Normal Range: "+normalRange(height, unitType, h));
            System.out.println("Overweight Range: "+overweightRange(height, unitType, h));
            System.out.println("Obese Range: "+obeseRange(height, unitType, h));

        } else if (unitType.equalsIgnoreCase("i")) {
            System.out.print("Enter your height in inches: ");
            height = sc.nextDouble();

            //convert height to meters^2
            height = h.centimetersToMeters(h.inchesToCentimeters(height));
            height = Math.pow(height, 2);

            // collect and convert weight to kilograms
            System.out.print("Enter your weight in pounds: ");
            weight = sc.nextDouble();
            weight = h.poundsToKilograms(weight);  //weight in kilograms

            bmiCalc(height, weight); // prints to results to console

            System.out.println("Underweight Range: "+underweightRange(height, unitType, h));
            System.out.println("Normal Range: "+normalRange(height, unitType, h));
            System.out.println("Overweight Range: "+overweightRange(height, unitType, h));
            System.out.println("Obese Range: "+obeseRange(height, unitType, h));

        } else {
            System.out.println("Invalid input");
        }


    }
}
