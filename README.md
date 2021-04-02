This project contains a library module called CustomButton

Application Behaviour
1. Project is done as required
2. When app runs a login screen will appear
3. There is a button called "register now" will take to the SignUp screen.
4. After signup user will be redirected to the login screen again
5. After loging in Home Screen will appear.
6. If app is in the foreground it will go to the login screen after 10 seconds
7. If app is in the background (by pressing android's home or O button) it will automatically navigate to the login screen after 30 seconds
8. Unit test of the project is written in test directory

CustomButton module guideline
1. Above project used a button from this module
2. It has three major xml attributes
   a. app:title (String)
   b. app:subtitle (String)
   c. app:icon_image (image reference)
by using those attributes a button can be customized for different purpose.
please refer to the Application layout XML files to get a clear intigration process
