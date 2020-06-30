# itechart-lab
upload app
=======================
An example Java application. Work accoding to Task 3.

Getting Started
---------------
This application deploy to heroku, https://hidden-dusk-48910.herokuapp.com/

Step for deploy
---------------
1. Create war file in Maven.
2. Install the Heroku Toolbelt. You can do this on heroku.com
3. Open the command prompt (for Windows) and type $heroku login
it will prompt to enter user name and password. Enter the credentials of your heroku account.
4. Now, to install the heroku-deploy plugin, enter the code:
$ heroku plugins:install heroku-cli-deploy
5. Now deploy the war file to the heroku server by this code:
$ heroku deploy:war --war <path_to_war_file> --app <app_name>
5.1 If you are in an application directory, the --app parameter can be omitted:
$ heroku deploy:war --war <path_to_war_file>

For more information: https://devcenter.heroku.com/articles/war-deployment