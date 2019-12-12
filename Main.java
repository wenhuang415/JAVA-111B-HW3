/*
On my honor, as a CCSF student, I, Wen Huang,
have neither given or received inappropriate help
with this assignment.
TODO fix addEntry
*/
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
class Main {
  static String MENU = "Student Activity Tracker Menu\n\"N\" -- track a new activity\n\"S\" -- show activities/files\n\"V\" -- view an activity\n\"A\" -- add an entry to an activity\n\"E\" -- edit activity entries\n\"F\" -- find in activity files\n\"Q\" -- quit Activity Tracker\n";
  static ArrayList<studentActivity> activities;
  public static studentActivity trackNewActivity() {
    //ask user for info
      String name,campus,course,duration;
      Scanner scan = new Scanner(System.in);
      System.out.println("\nEnter the name of the activity\n");
      name = scan.nextLine();
      System.out.println("\nEnter the campus\n");
      campus = scan.nextLine();
      System.out.println("\nEnter the course\n");
      course = scan.nextLine();
      System.out.println("\nTime Estimate(in hours h or minutes m)\n");
      duration = scan.nextLine();
      studentActivity s = new studentActivity(name,campus,course,duration);
      //create file and print summary into it
    try{
        FileWriter fw = new FileWriter(s.fileName);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(s.getSummary());
        System.out.println(s.activityName + " will be tracked in file named " + s.fileName+".\n" );
        pw.close();
    } 
    catch(IOException e) {
      System.out.println("Error while writing");
    }
    return s;
  }

  //method to add entry to activities
  public static void addEntry() {
    String fileName, description, duration;
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter file name to add entry to");
    fileName =  sc.nextLine();
    boolean found = false;
    //loop through activtities to find file
    int idx = 0;
    for(int i = 0; i < activities.size(); i++) {
      if(activities.get(i).fileName.equals(fileName)) {
        found = true;
        idx = i;
      }
    }
    if(!found) {
      System.out.println("No file found");
    }
    if(found) {
      System.out.println("Describe what you did: ");
      description = sc.nextLine();
      System.out.println("Duration (in minutes m or hours h): ");
      duration = sc.nextLine();
      //create entry with filled in parameters then add into specific activity
      studentActivityEntry e = new studentActivityEntry(description, duration);
      activities.get(idx).addEntry(e);
      //keeps asking the user for collaborators until they enter an empty string
      while(true) {
        String collaborator;
        System.out.println("Enter collaborator or just hit enter if none left");
        collaborator = sc.nextLine();
        if(!collaborator.equals("")){
           e.addCollaborator(collaborator);
        }
        else if(collaborator.equals("")) {
          break;
        }
      }
      //after entering entry print entry info into the file
      try {
        FileWriter fw = new FileWriter(activities.get(idx).fileName);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(e.getSummary());
        pw.close();
      } catch(IOException ex) {
        System.out.println("Error while writing to " + activities.get(idx).fileName);
      }
    }
  }

  
  public static String showActivities() {
    String s = "";
    for(int i = 0; i < activities.size(); i++) {
      s+= activities.get(i).getSummary();
    }
    return s;
  }

  public static void main(String[] args) {
      activities = new ArrayList<studentActivity>();
      //studentActivityEntry e = new studentActivityEntry("talk","1m");
      //System.out.println(e.getSummary());
      activities.add(trackNewActivity());
      addEntry();
      System.out.println(showActivities());
      //this is how you print out entry summary
      //activities[].entries[].getsummary
      System.out.println(activities.get(0).entries.get(0).getSummary());
      //System.out.println(MENU);
      
      /*
      Scanner scan = new Scanner(System.in);
      String f = scan.nextLine();
      if(f == "f") {
        c = false;
      }
      scan.close();
      //System.out.println(showActivities());*/

    

    /*
    studentActivity s = new studentActivity("Midterm 2 :(","CCSF","111B","");
    studentActivity s1 = new studentActivity("finals!","OCEAN","111B","3h");
    activities.add(s);
    activities.add(s1);
    studentActivityEntry e = new studentActivityEntry("study for exams", 9);
    studentActivityEntry e1 = new studentActivityEntry("study for tests", 121);
    e.addCollaborator("cody");
    e.addCollaborator("joanne");
    activities.get(0).addEntry(e);
    activities.get(0).addEntry(e1);
    activities.get(1).addEntry(e);
    //activities.get(1).addEntry(e);

    for(int i = 0; i < activities.size(); i++) {
      //found is index for entries
      //i is index for activities
      //String found = activities.get(i).searchBySubstring("study");
      String found = activities.get(i).searchByDuration("10m", "2h");
      System.out.println(found);
    }*/

    /*
    String toBeSearched = e.getSummary();
    System.out.println(toBeSearched);
    int found = toBeSearched.indexOf("T");
    int found2 = toBeSearched.indexOf("cody");
    System.out.println(found);
    System.out.println(found2);
    */

    //String summary = activities.get(0).studentActivitySummary();
    //System.out.println(summary);

    /*TODO
      make file writer
      set up user interface
      gui?
    */

    /*
    studentActivity s = new studentActivity("Midterm 2!","Ocean","111B","120m");
    studentActivityEntry e = new studentActivityEntry("talk", 500);
    e.addCollaborator("cody");
    e.addCollaborator("joanne");
    s.addEntry(e);
    studentActivityEntry e1 = new studentActivityEntry("study", 1);
    s.addEntry(e1);
    s.setStatus(2);
    //System.out.println(e.entrySummary());
    System.out.println(s.studentActivitySummary());
    //System.out.println(s.getEstimatedMinutes());
    */


  }
}
