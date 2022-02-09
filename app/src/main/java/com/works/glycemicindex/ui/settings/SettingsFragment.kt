package com.works.glycemicindex.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.works.glycemicindex.MainActivity
import com.works.glycemicindex.databinding.FragmentSettingsBinding
import com.works.glycemicindex.db.DB


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val bind get() = _binding!!
    lateinit var db : DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = bind.root

        bind.textViewDelete.setText("Veritabanını silmek, uygulamanın içindeki tüm tabloların ilk hallerine dönmesini sağlayacaktır. " +
                "\n\nSilme işlemi için aşağıdaki kutuyu seçmeniz gerekmektedir")

        bind.checkBox.setOnCheckedChangeListener { compoundButton, b ->
            if(compoundButton.isChecked)
            {
                bind.btnDelete.isEnabled = true
            }
            if(!compoundButton.isChecked)
            {
                bind.btnDelete.isEnabled = false
            }
        }

        bind.btnDelete.setOnClickListener {
            bind.root.context.deleteDatabase("project.db")
            Toast.makeText(bind.root.context, "Veritabanı Silindi", Toast.LENGTH_SHORT).show()
            bind.btnDelete.isEnabled = false

            val intent = Intent(bind.root.context, MainActivity::class.java)
            bind.root.context.startActivity(intent)
            activity?.finishAffinity()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}