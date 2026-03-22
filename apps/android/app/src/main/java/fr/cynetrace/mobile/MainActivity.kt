package fr.cynetrace.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fr.cynetrace.mobile.ui.CyneTraceApp
import fr.cynetrace.mobile.ui.theme.CyneTraceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as CyneTraceApplication

        setContent {
            CyneTraceTheme {
                CyneTraceApp(repository = app.repository)
            }
        }
    }
}
