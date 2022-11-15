package com.developer.kulitku.ui.home


import android.content.Intent
import com.developer.kulitku.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import com.developer.kulitku.ui.login.LoginFragment
import com.developer.kulitku.ui.register.PersonalInfoFragment
import com.developer.kulitku.ui.register.RegisterActivity

class HomeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val btnLogin: Button = view.findViewById(R.id.btnSignIn)
        val btnRegister: Button = view.findViewById(R.id.btnSIgnUp)
        btnLogin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnSignIn) {
            val mCategoryFragment = LoginFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(
                    R.id.frame_container,
                    mCategoryFragment,
                    LoginFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        } else if (v.id == R.id.btnSIgnUp) {
            requireActivity().run{
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
        }
    }

}