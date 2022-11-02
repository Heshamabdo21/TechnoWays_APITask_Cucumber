$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("End2End_Test.feature");
formatter.feature({
  "line": 3,
  "name": "automate  End2End API test",
  "description": "\t\t\tI want to print a user Email and check the exist posts and add new post",
  "id": "automate--end2end-api-test",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 7,
  "name": "Add new post using random userID",
  "description": "",
  "id": "automate--end2end-api-test;add-new-post-using-random-userid",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "Get the random userID",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "users API endpoint is exists",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I print a user Email",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "Verify a user posts ID between 1and100",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Add new Post using same userID",
  "keyword": "Then "
});
formatter.match({
  "location": "Steps.Get_the_random_userID()"
});
formatter.result({
  "duration": 58671699,
  "status": "passed"
});
formatter.match({
  "location": "Steps.users_API_endpoint_is_exists()"
});
formatter.result({
  "duration": 1723907100,
  "status": "passed"
});
formatter.match({
  "location": "Steps.I_print_a_user_Email()"
});
formatter.result({
  "duration": 1107218100,
  "status": "passed"
});
formatter.match({
  "location": "Steps.Verify_a_user_posts_ID_between_1and100()"
});
formatter.result({
  "duration": 1131833900,
  "status": "passed"
});
formatter.match({
  "location": "Steps.Add_new_Post_using_same_userID()"
});
formatter.result({
  "duration": 791451300,
  "status": "passed"
});
});