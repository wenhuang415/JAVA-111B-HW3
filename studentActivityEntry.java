/*
On my honor, as a CCSF student, I, Wen Huang,
have neither given or received inappropriate help
with this assignment.
*/
import java.util.ArrayList;
import java.util.Date;
public class studentActivityEntry {
  //instance variables
  String description;
  int duration;//in minutes
  ArrayList<String> collaborators;
  Date timestamp;

  //constructor
  public studentActivityEntry(String des,String t) {
    description = des;
    if(t.equals("")) {duration = 0;}
    else {
      String num = t.substring(0,t.length()-1);
      
      if(t.charAt(t.length()-1) == 'm') {
      duration = Integer.parseInt(num);
     }
     if(t.charAt(t.length()-1) == 'h') {
        duration = 60*Integer.parseInt(num);
     } 
    }
    timestamp = new Date();
    collaborators = new ArrayList<String>(20);
  }//constructor

  //method to add collaborators
  public void addCollaborator(String c) {
   collaborators.add(c);
  }//addCollaborator

  //setters to edit entry description
  public void editDescription(String d) {
    description = d;
  }//editDescription

  //setter to edit entry duration
  public void editDuration(int d) {
    duration = d;
  }//editDuration

  //method to convert minutes to hour minutes
  String formatTime(int numMins) {
    String formatted = "";
    int hours = numMins/60;
    int minutes = numMins%60;
    String hr = String.valueOf(hours) + "h";
    String min = String.valueOf(minutes) + "m";
    if(hours > 0 && minutes > 0)
    formatted = hr+" " + min;
    else if(hours > 0 && minutes == 0)
    formatted = hr;
    else if(hours == 0 && minutes > 0)
    formatted = min;
    return formatted;
  }

  
  //summarize entry
  String getSummary(){
    String formatted = formatTime(duration);
    String s = "";
    s+=timestamp.toString();
    s+=",";
    s+=formatted+", \"";
    s+=description;
    if(collaborators.isEmpty()) {
      s+="\"\nCollaborators: none";
    } else {
      s+="\"\nCollaborators: " + collaborators;
    }
    return s;
  }//entrySummary
}
