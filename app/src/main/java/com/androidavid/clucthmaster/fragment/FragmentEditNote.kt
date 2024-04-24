package com.androidavid.clucthmaster.fragment

import InterstitialAdManager
import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.androidavid.clucthmaster.MainActivity
import com.androidavid.clucthmaster.R
import com.androidavid.clucthmaster.databinding.FragmentEditNoteBinding
import com.androidavid.clucthmaster.model.Note
import com.androidavid.clucthmaster.viewmodel.NoteViewModel



class FragmentEditNote : Fragment(R.layout.fragment_edit_note), MenuProvider {

    private var editNoteBinding: FragmentEditNoteBinding? = null
    private  val binding get() = editNoteBinding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var currentNote : Note
    private lateinit var interstitialAdManager: InterstitialAdManager

    private val args: FragmentEditNoteArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        interstitialAdManager = InterstitialAdManager.getInstance(requireContext())
        interstitialAdManager.loadInterstitialAd(requireActivity())

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteDesc.setText(currentNote.noteDesc)

        binding.editNoteFab.setOnClickListener {
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteDesc = binding.editNoteDesc.text.toString().trim()

            if (noteTitle.isNotEmpty()){
                val note = Note(currentNote.id,noteTitle,noteDesc)
                notesViewModel.updateNote(note)
                interstitialAdManager.showInterstitialAd(requireActivity())
                view.findNavController().popBackStack(R.id.fragmentNotes,false)
            }else{
                Toast.makeText(context,"Por favor ingresa nota al titulo ", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Eliminar")
            setMessage("Estas seguro de Eliminar esta Nota o Referencia?")

            setPositiveButton("Eliminar"){_,_ ->
                notesViewModel.deleteNote(currentNote)
                Toast.makeText(context,"Referencia Eliminada ", Toast.LENGTH_LONG).show()
                view?.findNavController()?.popBackStack(R.id.fragmentNotes,false)
            }
            setNegativeButton("Cancelar", null)

        }.create().show()
    }
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.edit_note_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu -> {
                deleteNote()
                true
            }
            else -> false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }
}