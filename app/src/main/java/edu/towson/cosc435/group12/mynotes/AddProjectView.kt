package edu.towson.cosc435.group12.mynotes

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.util.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class,
    ExperimentalPermissionsApi::class
)
@Composable
fun AddProjectView(
    navController: NavHostController,
    projectvm: ProjectListViewModel
) {
    val projectDatabase = ProjectDatabase.getInstance(LocalContext.current)
    val focusManager = LocalFocusManager.current
    var sendNotification by remember { mutableStateOf(false) }
    var projectName by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
        .fillMaxSize()
        .clickable(onClick = { focusManager.clearFocus() }),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val keyboardController = LocalSoftwareKeyboardController.current

            TextField(
                value = projectName,
                onValueChange = { projectName = it },
                label = { Text("Project Name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() })
            )

            Button(
                onClick = {
                    val projectDao = projectDatabase.projectDao()
                    val newProjectId = UUID.randomUUID().toString()
                    val newProject = Project(newProjectId, projectName)

                    projectvm.addProject(newProject)
                    projectvm.addProjectDB(projectDao, newProject)

                    sendNotification = true
                    navController.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Create",
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }

    if (sendNotification) {
        val notification = createNotification(LocalContext.current, projectName)

        val notificationPermission = rememberPermissionState(
            permission = Manifest.permission.POST_NOTIFICATIONS
        )

        if (notificationPermission.status.isGranted) {
            //TODO: not sure why this is happening
            NotificationManagerCompat.from(LocalContext.current).notify(1, notification)
        } else {
            LaunchedEffect(key1 = true) {
                notificationPermission.launchPermissionRequest()
            }
        }
        sendNotification = false
    }
}