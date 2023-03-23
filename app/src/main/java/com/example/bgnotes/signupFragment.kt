package com.example.bgnotes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.bgnotes.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class signupFragment : Fragment(R.layout.fragment_signup) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    private lateinit var binding: FragmentSignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupBinding.bind(view)

        firebaseAuth=FirebaseAuth.getInstance()
        binding.tvSignin.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_container, loginFragment()).addToBackStack(null).commit()
        }
        binding.btSignup.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etRetypePassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password==confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.nav_container, loginFragment()).addToBackStack(null).commit()


                        }else{
                            Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                } else {
                    Toast.makeText(requireContext(), "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()

            }

        }
    }
}