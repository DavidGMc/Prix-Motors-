package com.androidavid.prixmotors.fragment

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
import com.androidavid.prixmotors.R
import com.androidavid.prixmotors.databinding.FragmentEditNoteBinding
import com.androidavid.prixmotors.model.Note
import com.androidavid.prixmotors.ui.MainActivity
import com.androidavid.prixmotors.viewmodel.NoteViewModel



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
                Toast.makeText(context,R.string.ingresa_un_titulo, Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle(getString(R.string.eliminar_nota_title))
            setMessage(getString(R.string.message_delete_nota))

            setPositiveButton(getString(R.string.eliminar_nota)){ _, _ ->
                notesViewModel.deleteNote(currentNote)
                interstitialAdManager.showInterstitialAd(requireActivity())
                Toast.makeText(context, getString(R.string.referencia_eliminada), Toast.LENGTH_LONG).show()
                view?.findNavController()?.popBackStack(R.id.fragmentNotes,false)
            }
            setNegativeButton(getString(R.string.cancelar_nota), null)

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