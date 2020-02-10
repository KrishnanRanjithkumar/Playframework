1 Goal

Please write a JAVA-based PLAY application which can move project tasks respecting different rules.

2

The basics Projects can be planned by using a Gantt diagram. Therefore, projects are split into different tasks, which can be structured in a hierarchical way. This means that a task may have zero to an unlimited number of child tasks, but every task has just one parent task (except one main task which does not have a parent task). Child tasks must start at the same time or later than its parent task and they must finish at the same time or earlier than its parent task. A task has a unique ID, a name and two date values (no time) – its start and its finish. The finish is always after the start. The tasks may be connected with constraints. These constraints are directed and may connect on either side of each task (start or finish), so that 8 different combinations are possible. A constraint is requiring the point at the target side to be at the same time or later than the point at the source side.

3 Your tasks

3.1 JAVA and PLAY Please familiarize yourself with the PLAY framework (https://www.playframework.com/) and inform yourself about this topic if necessary.

3.2 Create your own application

Create your own application. This application doesn’t need any user interface, but must provide a REST interface based on HTTP / JSON. You may use Postman to access this interface. The REST interface should cover the following functions: UPLOAD a project plan as JSON object to your application. You’re free how to define the structure of your JSON objects. Any uploaded project plan must be checked whether the rules described in 2 apply. If not, it should be rejected. It is not necessary to store the plan; it may be discarded when the program is shut down. DOWNLOAD a previously uploaded project plan as JSON object. https://www.playframework.com/documentation/2.8.x/NewApplication may help.

3.3 Manipulate tasks

Extend the REST interface to provide a third functionality: A task, its start or its end should be moved by a number of days. Your application should keep the project plan in a correct manner, that means, all rules described in 2 should still be followed. This should be achieved by changing only the fewest things possible in your plan.

3.4 Code quality

Ensure good code quality and document your code.

3.5 Further thoughts

Think about concurrent calls to your API and how you would deal with it while allowing as much parallelism / performance as possible. You do not have to implement it.
