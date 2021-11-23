package gallego.morales.myapptest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import gallego.morales.myapptest.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private var charsExist = ArrayList<Char>()//list of characters representing attempts
    private var lives: Int = 0
    private var countOk = 0
    private var string = ""
    private var listSet = listOf(
        "Super man",
        "Capitana Marvel",
        "Doctor strange",
        "Hulk",
        "Batman",
        "Thor",
        "Iron man"
    )

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val listDraw = listOf(
        R.drawable.ahorcado0,
        R.drawable.ahorcado1,
        R.drawable.ahorcado2,
        R.drawable.ahorcado3,
        R.drawable.ahorcado4,
        R.drawable.ahorcado5,
        R.drawable.ahorcado6
    )

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        (activity as MainActivity?)?.supportActionBar?.title = "El ahorcado"
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        string = getRandomString()
        binding.buttonIntento.setOnClickListener {
            val letter: Char = binding.casilleroLetra.text.toString()[0]
            val result: String = replaceCheckLetter(string, letter)
            binding.textView.text = result
            binding.imagenJugador.setImageResource(listDraw[lives])

            if (lives == 6) {
                findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
            }
            if (result == string) {
                findNavController().navigate(R.id.action_SecondFragment_to_fourFragment)
            }

        }
        binding.imagenJugador.setImageResource(listDraw[lives])
        binding.textView.text = replaceLetter(string)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRandomString(): String {
        return listSet.random()
    }

    private fun replaceLetter(str: String): String {
        val chars: CharArray = str.toCharArray()
        for (i in chars.indices) {
            if (chars[i] != ' ') {
                chars[i] = '_'
            }
        }
        return chars.concatToString()
    }

    private fun replaceCheckLetter(str: String, myChar: Char): String {
        val chars: CharArray = str.toCharArray()

        if (!chars.contains(myChar.uppercaseChar()) && !chars.contains(myChar.lowercaseChar())) {
            lives++
        } else {
            if (!charsExist.contains(myChar)) {
                charsExist.add(myChar)
                countOk++
                println("Congratulations on your hit number: $countOk")
            }
        }
        for (i in chars.indices) {
            if (chars[i] != ' ' && chars[i] != myChar.lowercaseChar() && chars[i] != myChar.uppercaseChar()) {
                if (!charsExist.contains(chars[i].lowercaseChar()) && !charsExist.contains(chars[i].uppercaseChar())) {
                    chars[i] = '_'
                }
            }
        }
        return chars.concatToString()
    }
}