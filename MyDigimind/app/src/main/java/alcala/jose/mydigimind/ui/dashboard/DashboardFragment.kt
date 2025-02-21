package alcala.jose.mydigimind.ui.dashboard

import alcala.jose.mydigimind.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import alcala.jose.mydigimind.databinding.FragmentDashboardBinding
import alcala.jose.mydigimind.ui.Task
import alcala.jose.mydigimind.ui.home.HomeFragment
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import java.text.SimpleDateFormat

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        val root=inflater.inflate(R.layout.fragment_dashboard,container,false)

        val btn_time:Button=root.findViewById(R.id.btn_time)

        btn_time.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener=TimePickerDialog.OnTimeSetListener{timePicker,hour,minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text=SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()
        }

        val btn_save:Button=root.findViewById(R.id.btn_save)
        val et_titulo:EditText=root.findViewById(R.id.et_titulo)
        val checkMonday:CheckBox=root.findViewById(R.id.monday)
        val checkTuesday:CheckBox=root.findViewById(R.id.tuesday)
        val checkWednesday:CheckBox=root.findViewById(R.id.wednesday)
        val checkThursday:CheckBox=root.findViewById(R.id.thursday)
        val checkFriday:CheckBox=root.findViewById(R.id.friday)
        val checkSaturday:CheckBox=root.findViewById(R.id.saturday)
        val checkSunday:CheckBox=root.findViewById(R.id.sunday)


        btn_save.setOnClickListener {
            var title=et_titulo.text.toString()
            var time=btn_time.text.toString()
            var days=ArrayList<String>()

            if(checkMonday.isChecked)
                days.add("Monday")
            if(checkThursday.isChecked)
                days.add("Tuesday")
            if(checkWednesday.isChecked)
                days.add("Wednesday")
            if(checkThursday.isChecked)
                days.add("Thursday")
            if(checkFriday.isChecked)
                days.add("Friday")
            if(checkSaturday.isChecked)
                days.add("Saturday")
            if(checkSunday.isChecked)
                days.add("Sunday")

            var task=Task(title, days, time)

            HomeFragment.tasks.add(task)
            Toast.makeText(root.context, "new task added", Toast.LENGTH_SHORT).show()

        }
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer{

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}