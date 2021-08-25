PRE-INSTRUCTIONS

1. Open your MySQL Workbench and login to your localhost server.

2. Create a new schema.

3. **Note: You can create a new schema through the MySQL Command Line Client as well, 
   but you will have to search up how to do that on your own if that's your preference.**


INSTRUCTIONS

1. It is highly recommended to run this program on an IDE such as 
   Visual Studio Code, Eclipse, IntelliJ, etc.

2. Pull out the client folder from this main project and start a new
   project in your IDE with the client only.

3. Pull out the server folder from this main project and start a new
   project in your IDE with the server only.

4. In the server project, open the file MySQLCredentials.java

5. final String DB_URL = "jdbc:mysql://localhost:3306/______?serverTimezone=MST";

6. For the line above, erase the blank line (between "3306/" and "?") and replace
   it with the name of your schema from PRE-INSTRUCTIONS Step 2.

7. final String USERNAME = "";

8. For the line above, type in your localhost server username between the quotations.

9. final String PASSWORD = "";

10. For the line above, type in your localhost server password between the quotations.

11. Run the file CreateDatabase.java

12. If everything is done correctly so far, you should see the message
    "Database Successfully Created." in your program terminal (without error messages).

13. Run the file ServerController.java

14. You should see the message "Server is Running." in your program terminal.

15. In the client project, run the file ClientController.java

16. Enjoy!


EXTRA INFORMATION

1. If you want to switch users you have to close the program GUI window, 
   run ClientController.java again, and enter the Student ID of the other user.

2. There are 2 students in the current database with the 
   following login credentials listed below:

3. Student Name: Sara Flanders
   Student ID: 1001

4. Student Name: Sam Reinhart
   Student ID: 1002


CONTACT

1. Ahnaf Naheen: ahnaf.naheen@ucalgary.ca

2. Hamza Lodhi: hamza.lodhi@ucalgary.ca