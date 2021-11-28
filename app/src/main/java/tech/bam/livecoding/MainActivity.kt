package tech.bam.livecoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : ComponentActivity() {
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }

    // override fun onResume() {
    //     super.onResume()
    //     if (Settings.canDrawOverlays(this)) {
    //         // Launch service right away - the user has already previously granted permission
    //         launchMainService()
    //         finish()
    //     } else {
    //         // Check that the user has granted permission, and prompt them if not
    //         checkDrawOverlayPermission()
    //     }
    // }
    //
    // private val activityResultLauncher =
    //     registerForActivityResult(object : ActivityResultContract<Unit, Unit>() {
    //         override fun createIntent(context: Context, input: Unit) =
    //             Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
    //
    //         override fun parseResult(resultCode: Int, intent: Intent?) = Unit
    //     }) {
    //         if (Settings.canDrawOverlays(this)) launchMainService()
    //     }
    //
    // private fun checkDrawOverlayPermission() {
    //     if (!Settings.canDrawOverlays(this)) {
    //         activityResultLauncher.launch(Unit)
    //     }
    // }
    //
    // private fun launchMainService() {
    //     val intent = Intent(this, OverlayService::class.java)
    //     stopService(intent)
    //     startService(intent)
    // }
}
