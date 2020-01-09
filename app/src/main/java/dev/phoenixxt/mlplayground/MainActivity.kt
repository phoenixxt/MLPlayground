package dev.phoenixxt.mlplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.smartreply.FirebaseTextMessage
import com.google.firebase.ml.naturallanguage.smartreply.SmartReplySuggestionResult

class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val conversation = listOf(
			FirebaseTextMessage.createForLocalUser("hi, what's up?", System.currentTimeMillis()),
			FirebaseTextMessage.createForLocalUser("where are you at now?", System.currentTimeMillis())
			//FirebaseTextMessage.createForLocalUser("heading out now", System.currentTimeMillis() + 101)
		)

		val smartReply = FirebaseNaturalLanguage.getInstance().smartReply
		smartReply.suggestReplies(conversation)
			.addOnSuccessListener { result ->
				if (result.status == SmartReplySuggestionResult.STATUS_SUCCESS) {
					Toast.makeText(this, result.suggestions.firstOrNull()?.text ?: "", Toast.LENGTH_LONG).show()
					result.suggestions.forEach {
						Log.e("asdasd", it.text)
					}
				}
			}
	}
}
