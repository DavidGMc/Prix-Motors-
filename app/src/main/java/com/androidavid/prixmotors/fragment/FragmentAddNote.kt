package com.androidavid.prixmotors.fragment

import InterstitialAdManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.databinding.FragmentAddNoteBinding
import com.androidavid.prixmotors.model.Note
import com.androidavid.prixmotors.ui.MainActivity
import com.androidavid.prixmotors.viewmodel.NoteViewModel


class FragmentAddNote : Fragment(R.layout.fragment_add_note), MenuProvider {

    private var addNoteBinding: FragmentAddNoteBinding? = null
    private  val binding get() = addNoteBinding!!
     private lateinit var notesViewModel: NoteViewModel
     private lateinit var addNoteView : View
    private lateinit var interstitialAdManager: InterstitialAdManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addNoteBinding = FragmentAddNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        interstitialAdManager = InterstitialAdManager.getInstance(requireContext())
        interstitialAdManager.loadInterstitialAd(requireActivity())

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).noteViewModel
        addNoteView = view
    }

    private fun saveNote(view:View){

        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

        if (noteTitle.isNotEmpty()){
            val note = Note(0,noteTitle,noteDesc)
            notesViewModel.addNote(note)
            interstitialAdManager.showInterstitialAd(requireActivity())

            Toast.makeText(addNoteView.context,
                getString(R.string.referencia_guardada), Toast.LENGTH_LONG).show()

            view.findNavController().popBackStack(R.id.fragmentNotes,false)
        }else{
            Toast.makeText(addNoteView.context,
                getString(R.string.ingresa_un_titulo), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.add_note_menu,menu)


    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu -> {
                saveNote(addNoteView)
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }

}