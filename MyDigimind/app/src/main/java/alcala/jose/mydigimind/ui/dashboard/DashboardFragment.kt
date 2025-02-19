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
import androidx.lifecycle.Observer

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        val root=inflater.inflate(R.layout.fragment_dashboard,container,false)



        dashboardViewModel.text.observe(viewLifecycleOwner, Observer{

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}