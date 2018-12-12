package io.sudheer.practice.simple;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateIPAddress {

    private Pattern ipPattern;
    private int[] arr=new int[4];
    private int i=0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter IP Address: ");

        //Input IP Address from user
        String ipAddress = scan.nextLine();
        scan.close();
        //IPAddress obj = new IPAddress();

        //Regex for IP Address
        //obj.ipPattern = Pattern.compile("((([0-1]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([0-1]?\\d\\d?|2[0-4]\\d|25[0-5]))");

        //Display output
        //System.out.println(ipAddress + obj.validateIpAddress(ipAddress));
    }

    //Method to check validity
    private String validateIpAddress(String ipAddress) {
        Matcher ipMatcher=ipPattern.matcher(ipAddress);

        //Condition to check input IP format
        if(ipMatcher.matches()) {
            //Split input IP Address on basis of .
            String[] octate=ipAddress.split("[.]");
            for(String x:octate) {

                //Convert String number into integer
                arr[i]=Integer.parseInt(x);
                i++;
            }

            if(arr[0]<=127) {
                //Check whether input is Class A IP Address or not
                if(arr[0]==0||arr[0]==127)
                    return(" is Reserved IP Address of Class A");
                else if(arr[1]==0&&arr[2]==0&&arr[3]==0)
                    return(" is Class A Network address");
                else if(arr[1]==255&&arr[2]==255&&arr[3]==255)
                    return( " is Class A Broadcast address");
                else
                    return(" is valid IP Address of Class A");
            } else if(arr[0]>=128&&arr[0]<=191) {
                //Check whether input is Class B IP Address or not
                if(arr[2]==0&&arr[3]==0)
                    return(" is Class B Network address");
                else if(arr[2]==255&&arr[3]==255)
                    return(" is Class B Broadcast address");
                else
                    return(" is valid IP Address of Class B");
            } else if(arr[0]>=192&&arr[0]<=223) {
                //Check whether input is Class C IP Address or not
                if(arr[3]==0)
                    return(" is Class C Network address");
                else if(arr[3]==255)
                    return(" is Class C Broadcast address");
                else
                    return( " is valid IP Address of Class C");
            } else if(arr[0]>=224&&arr[0]<=239) {
                //Check whether input is Class D IP Address or not
                return(" is Class D IP Address Reserved for multicasting");
            } else  {
                //Execute if input is Class E IP Address
                return(" is Class E IP Address Reserved for Research and Development by DOD");
            }
        } else {
            //Input not matched with IP Address pattern
            return(" is Invalid IP Address");
        }
    }
}
