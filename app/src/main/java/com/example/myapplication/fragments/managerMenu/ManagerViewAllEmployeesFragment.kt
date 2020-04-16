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
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.myapplication.R
import com.example.myapplication.activities.ManagerActivity
import com.example.myapplication.apipackage.Employee
import com.example.myapplication.apipackage.ResponseEmployees
import com.example.myapplication.apipackage.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ManagerViewAllEmployeesFragment : Fragment() {

    var index = 0
    var emps = List<Employee>(1) {
        Employee(
            0,
            "",
            "",
            0,
            "",
            0,
            0
        )
    }

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
                    emps = response.body()!!.employees
                    view.findViewById<TextView>(R.id.employee_position).apply{ text = (index+1).toString() + " OF " + emps.size.toString() }
                }
            })

            index = 0
            view.findViewById<EditText>(R.id.employee_id).apply { hint = emps[index].id.toString() } //int
            view.findViewById<EditText>(R.id.employee_password).apply { hint = emps[index].password }
            view.findViewById<EditText>(R.id.employee_name).apply { hint = emps[index].name }
            view.findViewById<EditText>(R.id.employee_wage).apply { hint = emps[index].wage.toString() }
            view.findViewById<EditText>(R.id.employee_role).apply { hint = emps[index].role }
            view.findViewById<EditText>(R.id.employee_hours).apply { hint = emps[index].hours.toString() }
            view.findViewById<EditText>(R.id.employee_tips).apply { hint = emps[index].tips.toString() }
        }

        view.findViewById<Button>(R.id.leave_button).setOnClickListener{ (activity as ManagerActivity).replaceFragment(ManagerViewEmployeeFragment(), "") }
        view.findViewById<Button>(R.id.update_employee).setOnClickListener{ empsUpdate() }
        view.findViewById<Button>(R.id.previous_button).setOnClickListener{ empsPrevious() }
        view.findViewById<Button>(R.id.next_button).setOnClickListener{ empsNext() }

        return view
    }

    fun empsUpdate() {
        val empId = emps[index].id
        var pass = "" //password
        if (view?.findViewById<EditText>(R.id.employee_password)?.hint.isNullOrEmpty()) {pass = emps[index].password}
        else {
            pass = view?.findViewById<EditText>(R.id.employee_password)?.hint.toString()
            emps[index].password = view?.findViewById<EditText>(R.id.employee_password)?.hint.toString()
        }
        var ename = "" //name
        if (view?.findViewById<EditText>(R.id.employee_name)?.hint.isNullOrEmpty()) {ename = emps[index].name}
        else {
            ename = view?.findViewById<EditText>(R.id.employee_name)?.hint.toString()
            emps[index].name = view?.findViewById<EditText>(R.id.employee_name)?.hint.toString()
        }
        var ewage = 0 //wage
        if (view?.findViewById<EditText>(R.id.employee_wage)?.hint.isNullOrEmpty()) {ewage = emps[index].wage}
        else {
            ewage = view?.findViewById<EditText>(R.id.employee_wage)?.hint.toString().toInt()
            emps[index].wage = view?.findViewById<EditText>(R.id.employee_wage)?.hint.toString().toInt()
        }
        var erole = "" //role
        if (view?.findViewById<EditText>(R.id.employee_role)?.hint.isNullOrEmpty()) {erole = emps[index].role}
        else {
            erole = view?.findViewById<EditText>(R.id.employee_role)?.hint.toString()
            emps[index].role = view?.findViewById<EditText>(R.id.employee_role)?.hint.toString()
        }
        var ehours = 0 //hours
        if (view?.findViewById<EditText>(R.id.employee_hours)?.hint.isNullOrEmpty()) {ehours = emps[index].hours}
        else {
            ehours = view?.findViewById<EditText>(R.id.employee_hours)?.hint.toString().toInt()
            emps[index].hours = view?.findViewById<EditText>(R.id.employee_hours)?.hint.toString().toInt()
        }
        var etips = 0 //tips
        if (view?.findViewById<EditText>(R.id.employee_tips)?.hint.isNullOrEmpty()) {etips = emps[index].tips}
        else {
            etips = view?.findViewById<EditText>(R.id.employee_tips)?.hint.toString().toInt()
            emps[index].tips = view?.findViewById<EditText>(R.id.employee_tips)?.hint.toString().toInt()
        }
        //new data
        RetrofitClient.instance.updateEmp(empId, pass, ename, ewage, erole, ehours, etips)

    }
    fun empsPrevious() {
        if (index == 0) { index = emps.size - 1 }
        else index -= 1

        view?.findViewById<TextView>(R.id.employee_position)?.apply{ hint = (index+1).toString() + " OF " + emps.size.toString()}
        view?.findViewById<TextView>(R.id.employee_password)?.apply { hint = emps[index].password }
        view?.findViewById<TextView>(R.id.employee_name)?.apply { hint = emps[index].name }
        view?.findViewById<EditText>(R.id.employee_wage)?.apply { hint = emps[index].wage.toString()}
        view?.findViewById<TextView>(R.id.employee_role)?.apply { hint = emps[index].role }
        view?.findViewById<EditText>(R.id.employee_hours)?.apply { hint = emps[index].hours.toString()}
        view?.findViewById<EditText>(R.id.employee_tips)?.apply { hint = emps[index].tips.toString()}
    }
    fun empsNext() {
        if (index == emps.size - 1) index = 0
        else index += 1

        view?.findViewById<TextView>(R.id.employee_position)?.apply{ hint = (index+1).toString() + " OF " + emps.size.toString()}
        view?.findViewById<TextView>(R.id.employee_password)?.apply { hint = emps[index].password }
        view?.findViewById<TextView>(R.id.employee_name)?.apply { hint = emps[index].name }
        view?.findViewById<EditText>(R.id.employee_wage)?.apply { hint = emps[index].wage.toString()}
        view?.findViewById<TextView>(R.id.employee_role)?.apply { hint = emps[index].role }
        view?.findViewById<EditText>(R.id.employee_hours)?.apply { hint = emps[index].hours.toString()}
        view?.findViewById<EditText>(R.id.employee_tips)?.apply { hint = emps[index].tips.toString()}
    }

    private fun runGraidentAnimation(v: View) {
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.manager_view_all_employees)
        val animationDrawable = constraintLayout?.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}
