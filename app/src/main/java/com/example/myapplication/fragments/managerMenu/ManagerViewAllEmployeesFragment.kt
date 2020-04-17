package com.example.myapplication.fragments.managerMenu

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.CustomerAccountActivity
import com.example.myapplication.activities.ManagerActivity
import com.example.myapplication.apipackage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ManagerViewAllEmployeesFragment : Fragment() {

    var index = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_manager_view_all_employees, container, false)
        runGraidentAnimation(view)

        view.findViewById<Button>(R.id.get_employees_button).setOnClickListener {
            RetrofitClient.instance.getAllEmp().enqueue(object: Callback<ResponseEmployees> {
                override fun onFailure(call: Call<ResponseEmployees>, t: Throwable) {
                    view.findViewById<TextView>(R.id.employee_position).apply{ text = "UNSUCCESSFUL" }
                }
                override fun onResponse(call: Call<ResponseEmployees>, response: Response<ResponseEmployees>) {

                    val output = response.body()?.employees

                    if (output != null) {
                        view.findViewById<EditText>(R.id.employee_id).hint =
                            output.get(index).id.toString()
                    }

                    if (output != null) {
                        view.findViewById<EditText>(R.id.employee_name).hint   = output.get(index).name
                    }
                    if (output != null) {
                        view.findViewById<EditText>(R.id.employee_wage).hint = output.get(index).wage.toString()
                    }
                    if (output != null) {
                        view.findViewById<EditText>(R.id.employee_role).hint = output.get(index).role
                    }
                    if (output != null) {
                        view.findViewById<EditText>(R.id.employee_hours).hint = output.get(index).hours.toString()
                    }
                    if (output != null) {
                        view.findViewById<EditText>(R.id.employee_tips).hint = output.get(index).tips.toString()
                    }
                    if (output != null) {
                        view.findViewById<EditText>(R.id.employee_compmeals). hint = output.get(index).compmeals.toString()
                    }

                    view.findViewById<Button>(R.id.leave_button).setOnClickListener{ (activity as ManagerActivity).replaceFragment(ManagerViewEmployeeFragment(), "") }
                    view.findViewById<Button>(R.id.update_employee).setOnClickListener{ empsUpdate(output) }
                    view.findViewById<Button>(R.id.previous_button).setOnClickListener{ empsPrevious(output) }
                    view.findViewById<Button>(R.id.next_button).setOnClickListener{ empsNext(output) }

                }
            })
        }

        return view
    }

  fun empsUpdate(employeeList: List<Employee>?) {
        val empId = employeeList?.get(index)?.id
      //val empId = 1
       // var pass = "" //password
        var ename = "" //name
        if (view?.findViewById<EditText>(R.id.employee_name)?.hint.isNullOrEmpty()) {
            if (employeeList != null) {
                ename = employeeList.get(index).name
            }
        }
        else {
            ename = view?.findViewById<EditText>(R.id.employee_name)?.hint.toString()
            if (employeeList != null) {
                employeeList.get(index).name = view?.findViewById<EditText>(R.id.employee_name)?.hint.toString()
            }
        }
        var ewage = 0 //wage
        if (view?.findViewById<EditText>(R.id.employee_wage)?.hint.isNullOrEmpty()) {
            if (employeeList != null) {
                ewage = employeeList.get(index).wage
            }
        }
        else {
            ewage = view?.findViewById<EditText>(R.id.employee_wage)?.hint.toString().toInt()
            if (employeeList != null) {
                employeeList.get(index).wage = view?.findViewById<EditText>(R.id.employee_wage)?.hint.toString().toInt()
            }
        }
        var erole = "" //role
        if (view?.findViewById<EditText>(R.id.employee_role)?.hint.isNullOrEmpty()) {
            if (employeeList != null) {
                erole = employeeList.get(index).role
            }
        }
        else {
            erole = view?.findViewById<EditText>(R.id.employee_role)?.hint.toString()
            if (employeeList != null) {
                employeeList.get(index).role = view?.findViewById<EditText>(R.id.employee_role)?.hint.toString()
            }
        }
        var ehours = 0 //hours
        if (view?.findViewById<EditText>(R.id.employee_hours)?.hint.isNullOrEmpty()) {
            if (employeeList != null) {
                ehours = employeeList.get(index).hours
            }
        }
        else {
            ehours = view?.findViewById<EditText>(R.id.employee_hours)?.hint.toString().toInt()
            if (employeeList != null) {
                employeeList.get(index).hours = view?.findViewById<EditText>(R.id.employee_hours)?.hint.toString().toInt()
            }
        }
        var etips = 0.0 //tips
        if (view?.findViewById<EditText>(R.id.employee_tips)?.hint.isNullOrEmpty()) {
            if (employeeList != null) {
                etips =
                    employeeList.get(index).tips.toDouble()
            }
        }
        else {
            etips = view?.findViewById<EditText>(R.id.employee_tips)?.hint.toString().toDouble()
            if (employeeList != null) {
                employeeList.get(index).tips = view?.findViewById<EditText>(R.id.employee_tips)?.hint.toString().toDouble()
            }
        }
        var ecompmeals = 0 //compmeals
        if (view?.findViewById<EditText>(R.id.employee_compmeals)?.hint.isNullOrEmpty()) {
            if (employeeList != null) {
                ecompmeals = employeeList.get(index).compmeals
            }
        }
        else {
            ecompmeals = view?.findViewById<EditText>(R.id.employee_compmeals)?.hint.toString().toInt()
            employeeList?.get(index)?.compmeals  = view?.findViewById<EditText>(R.id.employee_compmeals)?.hint.toString().toInt()
        }
        //new data
      if (empId != null) {
          RetrofitClient.instance.updateEmp(empId, ename, ewage, erole, ehours, etips, ecompmeals)
              .enqueue(object : Callback<ResponseEmployee> {
                  override fun onFailure(call: Call<ResponseEmployee>, t: Throwable) {
                      Toast.makeText(
                          activity as ManagerActivity,
                          "Customer Already Exist",
                          Toast.LENGTH_LONG
                      ).show()
                  }

                  override fun onResponse(
                      call: Call<ResponseEmployee>,
                      response: Response<ResponseEmployee>
                  ) {
                      Toast.makeText(
                          activity as ManagerActivity,
                          "update sucessfull",
                          Toast.LENGTH_LONG
                      ).show()

                  }


              })

      }

    }
    fun empsPrevious(employeeList: List<Employee>?) {
        if (index == 0) {
            if (employeeList != null) {
                index = employeeList.size - 1
            }
        }
        else index -= 1



      view?.findViewById<TextView>(R.id.employee_position)?.apply{
            if (employeeList != null) {
                hint = (index+1).toString() + " OF " + employeeList.size.toString()
            }
        }

      view?.findViewById<TextView>(R.id.employee_id)?.apply { hint = employeeList?.get(index)?.id.toString()}
        view?.findViewById<TextView>(R.id.employee_name)?.apply { hint = employeeList?.get(index)?.name}
        view?.findViewById<EditText>(R.id.employee_wage)?.apply { hint =
            employeeList?.get(index)?.wage.toString()
        }
        view?.findViewById<TextView>(R.id.employee_role)?.apply { hint = employeeList?.get(index)?.role }
        view?.findViewById<EditText>(R.id.employee_hours)?.apply { hint = employeeList?.get(index)?.hours.toString()}
        view?.findViewById<EditText>(R.id.employee_tips)?.apply { hint = employeeList?.get(index)?.tips.toString()}
        view?.findViewById<EditText>(R.id.employee_compmeals)?.apply { hint = employeeList?.get(index)?.compmeals.toString() }
    }
    fun empsNext(employeeList: List<Employee>?) {
        if (employeeList != null) {
            if (index == employeeList.size - 1) index = 0
            else index += 1
        }

        view?.findViewById<TextView>(R.id.employee_position)?.apply{
            if (employeeList != null) {
                hint = (index+1).toString() + " OF " + employeeList.size.toString()
            }
        }

        view?.findViewById<TextView>(R.id.employee_id)?.apply { hint = employeeList?.get(index)?.id.toString()}
        view?.findViewById<TextView>(R.id.employee_name)?.apply { hint = employeeList?.get(index)?.name}
        view?.findViewById<EditText>(R.id.employee_wage)?.apply { hint =
            employeeList?.get(index)?.wage.toString()
        }
        view?.findViewById<TextView>(R.id.employee_role)?.apply { hint = employeeList?.get(index)?.role }
        view?.findViewById<EditText>(R.id.employee_hours)?.apply { hint = employeeList?.get(index)?.hours.toString()}
        view?.findViewById<EditText>(R.id.employee_tips)?.apply { hint = employeeList?.get(index)?.tips.toString()}
        view?.findViewById<EditText>(R.id.employee_compmeals)?.apply { hint = employeeList?.get(index)?.compmeals.toString() }
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.manager_view_all_employees)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}
