package com.example.shrine

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)

        // Snippet from "Navigate to the next Fragment" section goes here.

        val passwordEditText = view?.findViewById<EditText>(R.id.password_edit_text)
        val passwordTextInput = view?.findViewById<TextInputLayout>(R.id.password_text_input)
        val nextButton = view?.findViewById<Button>(R.id.next_button)


        nextButton?.setOnClickListener({
            if (!isPasswordValid(passwordEditText?.text!!)) {
                passwordTextInput?.error = getString(R.string.shr_error_password)
            } else {
                passwordTextInput?.error = null
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
            }
        })

        passwordTextInput?.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(passwordEditText?.text!!)) {
                passwordEditText.error = null
            }
            false
        }
        return view

    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
    private fun isPasswordValid(text: Editable?) : Boolean {
        return text != null && text.length >= 8
    }
}
