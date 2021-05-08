# DenTech 
#### *A Dental Clinic Management Application*

As the title suggests, this project is about a clinic management application that will be used on a
daily basis in a dental clinic by their staff. It provides an interface for clinic clerk, doctors,
assistants or hygienists in which they can manage patients' appointment, medical records and personal
data, access or manage staff's data and more. Every staff in the clinic will probably start their
workday by signing in to this application and end it by signing out!

My childhood interest towards the field of dentistry, passion in coding and previous background as a
medical student gathered to give birth to this project. Also, being raised in a medical family has made
me familiar with the general idea as well as many details that are desirable for clinic staff in such
application.

Some features of this application are as follows:
- Manage the dataset of registered patients with their previous medical records
- Manage the dataset of the clinic staff including doctors, hygienists, assistants and clerks
- Manage treatment details of each patient
- Manage clinic rooms and their equipments
- Obtain daily working hours, income as a summary for each of the clinic staff
- Login/logout of the staff
- Data persistence

Note: some feature above will be tentatively completed by the final submission.

##User Stories
Here are some possible user stories for this application:
- As a user, I want to add or remove patients from clinic database.
- As a user, I want to add or remove staff from the clinic database.
- As a user, I want to view data of a patient or staff in the clinic database.
- As a user, I want to edit data of a patient or staff in the clinic database.
- As a user, I want to view details of a patient's past visit reports in the clinic.
- As a user, I want to add or remove a procedure from patient's current visit report.
- As a user, I want to view all incomplete and complete tasks for a patient.
- As a user, I want all clinic data to be saved automatically before quitting the application.
- As a user, I want all previous clinic data to be loaded automatically when starting the application.
- As a user, I want to be able to clear all saved clinic data.
- As a user, I want to be able to save/load clinic data manually.

##Phase 4: Task 2

1 . Type hierarchy:

   In the ui package, Window is an abstract super class extending JPanel. Menu, Form, PersonListDisplay
   and SelectedStaffDisplay classes extend Window. The overridden abstract method is "setupGraphics"
   which has different implementation in each sub-class.

2 . Map interface:

   In model.clinic package, Clinic class stores Patients and Staff in HashMap, having their ID as key
   and the actual object as value.
   
##Phase 4: Task 3
- Use hashCode and Equals for identification of Staff and Patient instead of using getID method.
- Use type substitution for Staff and Patient with Person in Clinic class to reduce repetitive code.
- Sort list of Visit Reports in Patient class based on their date in addVisitReport method so that the
list is always in order of date.
- Instead of using index to get Room, Equipment or VisitReport, use a specified id, hashCode and equals.
- Use bi-directional association between Room and Equipment to access room number from Equipment to easily relocate it.
- Use bi-directional association between Patient and VisitReport.
- In LoadingScreen, create a new field for sound and load the sound file upon construction for faster
access when playing the sound. (use buffer)
- After creating GUI for PatientMenu, remove the association of it with Clinic and make it extend Menu.
- Rename PersonListDisplay to ListDisplay.
- Create a SelectedDisplay abstract class Extending Window and put SelectedStaffDisplay and SelectedPatientDisplay
as subclasses of it to reduce any repetition between the two classes.
- Make all Input classes in datafield package robust so that GUI inputs can handle all wrong inputs as well.
- Create a listener class called NavigationListener, that every button which navigates between pages in menu
Uses that.
- Remove the association between BackButton and Main and use the NavigationListener described above.
- Remove ButtonWithDescription abstract class since its only subclass is MenuNavigationButton.
- Use Observer Pattern between StaffListDisplay and Clinic and PatientListDisplay and Clinic instead of
having the menu button listener call updateList each time.
- Too much coupling between Window and all of its subclasses and Main only to be able to navigate through
pages. Separate the window management functionality of main to increase its cohesion and reduce so mentioned
coupling.
