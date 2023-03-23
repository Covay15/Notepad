package com.example.bgnotes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bgnotes.databinding.FragmentLoginBinding
import com.example.bgnotes.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class loginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        firebaseAuth= FirebaseAuth.getInstance()

        binding.tvSignup.setOnClickListener {
           requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_container, signupFragment()).addToBackStack(null).commit()
        }
        binding.btLogin.setOnClickListener {
            val loginEmail = binding.etEmailLogin.text.toString()
            val loginPassword = binding.etPasswordLogin.text.toString()

            if (loginEmail.isNotEmpty() && loginPassword.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_container, MyNoteFragment()).addToBackStack(null).commit()

                        } else {
                            Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}
