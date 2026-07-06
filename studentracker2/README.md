# Student Tracker (Swing GUI) — matched to your actual classes

This version uses your real `Student`, `Course`, and `StudentManager`
classes (package `studentracker`), not guessed ones.

## What changed in `StudentManager.java`
Only two small, non-breaking additions (clearly commented in the file):
1. `getAllStudents()` — returns the student list so the GUI can fill the table.
2. `findStudentById(short)` — changed from `private` to `public` so the
   GUI can fetch a `Student` object directly instead of only printing
   to console.

Nothing else changed. Your console app (if you still have the old
`StudentTrackerApp` with the `Scanner` loop) would keep working exactly
the same with this `StudentManager`.

## Files
- `studentracker/Student.java` — unchanged, exactly as you gave it
- `studentracker/Course.java` — unchanged, exactly as you gave it
- `studentracker/StudentManager.java` — your code + 2 additions above
- `studentracker/StudentTrackerGUI.java` — new, has `main`, run this one

## 1. Compile
From the folder that contains the `studentracker` folder:
```
javac -d build studentracker/*.java
```

## 2. Run
```
java -cp build studentracker.StudentTrackerGUI
```

## 3. Package into a runnable .jar to share with friends
Create `manifest.txt` with one line:
```
Main-Class: studentracker.StudentTrackerGUI
```
Build the jar:
```
jar cfm StudentTracker.jar manifest.txt -C build .
```
Send `StudentTracker.jar` to friends. They just need Java installed
(a JRE is enough) and can run it with:
```
java -jar StudentTracker.jar
```
or by double-clicking it.

## Known quirk
`calculateCGPA()` in your `StudentManager` prints a line to the
console each time it runs. The GUI calls it behind the scenes to
refresh the CGPA column, so you'll see console output pop up in the
terminal window if you run it from a terminal. Harmless — just extra
noise. Say the word if you'd like that console output removed/quieted.

## Data persistence
Still in-memory only — closing the app clears all data. Ask if you
want save/load to a file added.
