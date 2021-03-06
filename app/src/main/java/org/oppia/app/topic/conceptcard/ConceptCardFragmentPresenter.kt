package org.oppia.app.topic.conceptcard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.oppia.app.R
import org.oppia.app.databinding.ConceptCardFragmentBinding
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.viewmodel.ViewModelProvider
import javax.inject.Inject

/** Presenter for [ConceptCardFragment], sets up bindings from ViewModel */
@FragmentScope
class ConceptCardFragmentPresenter @Inject constructor(
  private val fragment: Fragment,
  private val viewModelProvider: ViewModelProvider<ConceptCardViewModel>
) {
  private lateinit var skillId: String

  /**
   * Sets up data binding and toolbar.
   * Host activity must inherit ConceptCardListener to dismiss this fragment.
   */
  fun handleCreateView(inflater: LayoutInflater, container: ViewGroup?, id: String): View? {
    val binding = ConceptCardFragmentBinding.inflate(inflater, container, /* attachToRoot= */ false)
    val viewModel = getConceptCardViewModel()

    skillId = id
    viewModel.setSkillIdAndBinding(skillId, binding)

    binding.conceptCardToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
    binding.conceptCardToolbar.setNavigationOnClickListener {
      (fragment.requireActivity() as? ConceptCardListener)?.dismiss()
    }

    binding.let {
      it.viewModel = viewModel
      it.lifecycleOwner = fragment
    }
    return binding.root
  }

  private fun getConceptCardViewModel(): ConceptCardViewModel {
    return viewModelProvider.getForFragment(fragment, ConceptCardViewModel::class.java)
  }
}
