/*
On my honor, as a CCSF student, I, Wen Huang,
have neither given or received inappropriate help
with this assignment.
*/
import java.util.ArrayList;
public class studentActivity {
  //instance variables
  String activityName;
  String campus;
  String course;
  ArrayList<studentActivityEntry> entries;
  int estimatedMinutes;
  enum activityStatus { ACTIVE, INACTIVE, COMPLETE};
  activityStatus status;
  String fileName;

  //writeFile()
  public void writeFile(String n) {
  }

  //method to format file name
  public static String format(String s) {
    s = s.toLowerCase();
    s = s.replace("!","");
    s = s.replace(",","");
    s = s.replace("''","");
    s = s.replace(":","");
    s = s.replace("@","");
    s = s.replace("$","");
    s = s.replace("*","");
    s = s.replace("#","");
    s = s.replace("%","");
    s = s.replace("^","");
    s = s.replace("&","");
    s = s.replace(".","");
    s = s.replace(":","");
    s = s.replace("(","");
    s = s.replace(")","");
    return s;
  }//format()

  //constructor
  public studentActivity(String n,String p,String c,String t) {
    fileName = n.replaceAll(" ", "_");
    fileName = fileName.replaceAll("/","-");
    fileName = format(fileName);

    entries = new ArrayList<studentActivityEntry>();
    activityName = n;
    campus = p;
    course = c;
    if(t == "") {estimatedMinutes = 0;}
    else {
      String num = t.substring(0,t.length()-1);
      if(t.charAt(t.length()-1) == 'm') {
      estimatedMinutes = Integer.parseInt(num);
     }
     if(t.charAt(t.length()-1) == 'h') {
        estimatedMinutes = 60*Integer.parseInt(num);
     } 
    }
    status = activityStatus.ACTIVE;
  }//constructor

  //method to change status
  void setStatus(int a) {
    if(a==1)
      status = activityStatus.ACTIVE;
    else if(a==2)
      status = activityStatus.INACTIVE;
    else if(a==3)
      status = activityStatus.COMPLETE;
    else {
      System.out.println("invalid input");
    }
  }//setStatus

  //method of add entries
  void addEntry(studentActivityEntry e) {
    entries.add(e);
  }//addEntry

  //method to remove entries
  void deleteEntry(int i) {
    entries.remove(i-1);
  }//deleteEntry
https://github.com/wenhuang415/JAVA-111B-HW3
  //method to sum up total duration of all entries
  int totalEntryDuration() {
    int sum = 0;
    for(int i = 0; i < entries.size(); i++) {
      sum+=entries.get(i).duration;
    }
    return sum;
  }

  //method to convert minutes to hour minutes
  String formatTime(int numMins) {
    String formatted = "";
    if(numMins == 0) {
      return "0m";
    }
    int hours = numMins/60;
    int minutes = numMins%60;
    String hr = String.valueOf(hours) + "h";
    String min = String.valueOf(minutes) + "m";
    if(hours > 0 && minutes > 0)
    formatted = hr+" " + min;
    else if(hours > 0 && minutes == 0)
    formatted = hr;https://github.com/wenhuang415/JAVA-111B-HW3
    else if(hours == 0 && minutes > 0)
    formatted = min;
    return formatted;
  }

  //getter for estimatedMinutes
  int getEstimatedMinutes() {
    return estimatedMinutes;
  }//getEstimatedMinutes

  //studentActivitySummary used to append into files
  String getSummary() {
    String timeEstimate = formatTime(estimatedMinutes);
    String totalTime = formatTime(totalEntryDuration());

    String sum = "File: " + fileName + " Activity: " + activityName + " Campus/Course: "  + campus+"/"+course+ " Status: " + status+"\nTime Estimate: " + timeEstimate+ " Current total time: " + totalTime+"\n";

    return sum;
  }

//helper method for searching to display an entry as a String
  String display(int n) {
    String s = "";
    s+="\nFile: " + fileName + " Activity: " + activityName +" Duration: ";
    String formatted = formatTime(entries.get(n).duration);
    s+= formatted +" Entry: " + entries.get(n).description;
    if(entries.get(n).collaborators.isEmpty()) {
      s+="\nCollaborators: none\n";
    } else {
      s+="\nCollaborators: " + entries.get(n).collaborators+"\n\n";
    }
    return s;
  }

  //search by substring method to search entries arrayList and returns concatenated string of entries if found
  String searchBySubstring(String s) {
    String summary = "";
    //int idx = -1;
    for(int i = 0; i < entries.size(); i++) {
      String toBeSearched = entries.get(i).getSummary();
      if(toBeSearched.indexOf(s) > -1) {
        summary += display(i);
      }
    }
    if(summary == "") {return "No matching entries in file: " + fileName + "\n"; }
    return summary;
  }

  //helpter method for searchByDuration to convert string into int 
  int convertDuration(String a) {
    String num1 = a.substring(0,a.length()-1);
    int duration = 0;
    if(a.charAt(a.length()-1) == 'h') {
      duration = Integer.parseInt(num1)*60;
    } else {
      duration = Integer.parseInt(num1);
    }
    return duration;
  }

  //search by duration
  String searchByDuration(String a, String b) {
    int r1 = convertDuration(a);
    int r2 = convertDuration(b);
    String summary = "";

    for(int i = 0; i < entries.size(); i++) {
      if(entries.get(i).duration >= r1 && entries.get(i).duration <= r2) {
        summary += display(i);
      }
    }

    if(summary == "") {return "No matching entries in file: " + fileName + "\n"; }
    return summary;
  }
  
}

