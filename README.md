# MyNotes
MyNotes is a comprehensive studying app designed to enhance your learning experience. With MyNotes, you can easily create projects and organize your study materials efficiently. Whether you're a student preparing for exams, a professional expanding your knowledge, or simply someone who enjoys self-improvement, MyNotes is your ultimate study companion.

The app offers a user-friendly interface where you can create projects corresponding to different subjects or topics. Within each project, you can create and manage a collection of notes, which act as your digital flashcards. Each note consists of a front and back side, allowing you to enter questions, prompts, or key concepts on the front and their corresponding answers or explanations on the back.

With MyNotes, studying becomes an engaging and interactive experience. You can access your projects and notes anytime, anywhere, making it convenient for both planned study sessions and spontaneous review moments. Say goodbye to disorganized study materials and hello to a streamlined studying process.

## Technoloy Used

- LazyColumn: to organize the project list, project edit, and search list views
- Notifications: notifies the user when a new project is created
- Dialogs: forces the user to confirm project deletion
- Intents: communicates with the user's browser app to connect them to help
- Navigation: multi-screen navigation with TopAppBar and resposive piloting
- SQLite: database implementation to store project and notes for retrieval upon app creation
- API Fetching: communication with JSON server API to fetch sample data

## Features
- Choice to automatically add sample data when no projects or notes are in database
- Help messages when no projects or notes are in database
- Add a project by selecting the floating add button in the bottom right, then selecting 'P'
- Add a note by selecting the same button, then selecting 'N'
- Delete project by tapping three vertical dots on the right of the card and selecting 'Delete', then 'Confirm'
- Edit project by tapping the same button and selecting edit, or long click the project card
- Delete a note by navigating to the edit project screen, stated above, and swiping desired note card right-to-left
- Edit project name or note contents by navigating to the same screen and selecting the pencil icon next to desired card
- Search for projects or notes by selecting the search icon in the top right
- Open browser app by navigating to the an edit or add screen and selecting the information button in the top left

## Resources
- Androidx Navigation (for navigation)
- Androidx Room (for SQLite and database implementation)
- Google Accompanist (for permission handling)
- OkHTTP (for API fetching)
- Gson (for JSON parsing)