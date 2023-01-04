PRE-INSTRUCTIONS

Open your MySQL Workbench and login to your localhost server.

Create a new schema.

Note: You can create a new schema through the MySQL Command Line Client as well, but you will have to search up how to do that on your own if that's your preference.

INSTRUCTIONS

It is highly recommended to run this program on an IDE such as Visual Studio Code, Eclipse, IntelliJ, etc.

Pull out the client folder from this main project and start a new project in your IDE with the client only.

Pull out the server folder from this main project and start a new project in your IDE with the server only.

In the server project, open the file MySQLCredentials.java

final String DB_URL = "jdbc:mysql://localhost:3306/______?serverTimezone=MST";

For the line above, erase the blank line (between "3306/" and "?") and replace it with the name of your schema from PRE-INSTRUCTIONS Step 2.

final String USERNAME = "";

For the line above, type in your localhost server username between the quotations.

final String PASSWORD = "";

For the line above, type in your localhost server password between the quotations.

Run the file CreateDatabase.java

If everything is done correctly so far, you should see the message "Database Successfully Created." in your program terminal (without error messages).

Run the file ServerController.java

You should see the message "Server is Running." in your program terminal.

In the client project, run the file ClientController.java

Enjoy!

EXTRA INFORMATION

If you want to switch users you have to close the program GUI window, run ClientController.java again, and enter the Student ID of the other user.

There are 2 students in the current database with the following login credentials listed below:

Student Name: Sara Flanders Student ID: 1001

Student Name: Sam Reinhart Student ID: 1002

CONTACT

Ahnaf Naheen: ahnaf.naheen@ucalgary.ca

Hamza Lodhi: hamza.lodhi@ucalgary.ca
