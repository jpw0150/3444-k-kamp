package com.example.myapplication.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activities.CustomerAccountActivity
import com.example.myapplication.activities.CustomerAdapter
import com.example.myapplication.apipackage.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomerLoginFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private val adapter: CustomerAdapter? = null
    private val userList: List<Customer>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_customer_login, container, false)
        runGraidentAnimation(view)

        /*val loginButton = view.findViewById<Button>(R.id.customer_login_button)
        //val database = (activity as CustomerAccountActivity).customer_db
        /*loginButton.setOnClickListener{
            /* Take user input */
            val username = view.findViewById<EditText>(R.id.customer_username).text.toString()
            val password = view.findViewById<EditText>(R.id.customer_password).text.toString()

            if ((activity as CustomerAccountActivity).verifyLogin(username, password)) {
                (activity as CustomerAccountActivity).currentCustomer = database.getCustomer(username)
                val intent = Intent (activity, MenuActivity::class.java)
                activity?.startActivity(intent)
            }
        }*/
*/
        val loginButton = view.findViewById<Button>(R.id.customer_login_button)

        loginButton.setOnClickListener {
            RetrofitClient.instance.allcustomers(
            )
                .enqueue(object : Callback<ResponseCustomers> {
                    override fun onFailure(call: Call<ResponseCustomers>, t: Throwable) {
                        Toast.makeText(
                            activity as CustomerAccountActivity,
                            "Customer Already Exist",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<ResponseCustomers>,
                        response: Response<ResponseCustomers>
                    ) {
                        val output = response.body()?.customers?.get(0)?.name
                        /*Toast.makeText(
                            activity as CustomerAccountActivity,
                            response.body()?.customer,
                            Toast.LENGTH_LONG
                        ).show()*/
                        view.findViewById<TextView>(R.id.textView11).text = output

                    }


                })

        }
        return view
    }


    private fun runGraidentAnimation(v: View) {
        val relativeLayout = v.findViewById<RelativeLayout>(R.id.fragment_customer_login)
        val animationDrawable = relativeLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }


}
