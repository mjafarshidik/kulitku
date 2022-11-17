package com.developer.kulitku.ui.scan

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.developer.kulitku.R
import java.io.File

class GalleryFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val imageFilePath = arguments?.getString(KEY_IMAGE_FILE_PATH)
//        if (imageFilePath.isNullOrBlank()) {
//            Navigation.findNavController(requireActivity(), R.id.preview).popBackStack()
//        } else {
//            (view as ImageView).setImageURI(Uri.parse(imageFilePath))
//        }
        val args = arguments ?: return
        val resource = args.getString(FILE_NAME_KEY)?.let { File(it) } ?: R.drawable.acne_girl
        Glide.with(view).load(resource).into(view as ImageView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    companion object {
//        private const val KEY_IMAGE_FILE_PATH = "key_image_file_path"
//        fun arguments(absolutePath: String): Bundle {
//            return Bundle().apply { putString(KEY_IMAGE_FILE_PATH, absolutePath) }
//        }
private const val FILE_NAME_KEY = "file_name"

        fun create(image: File) = GalleryFragment().apply {
            arguments = Bundle().apply {
                putString(FILE_NAME_KEY, image.absolutePath)
            }
        }
    }
}