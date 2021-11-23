package gallego.morales.myapptest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import gallego.morales.myapptest.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    var charsExist = ArrayList<Char>()//list of characters representing attempts
    var lives: Int = 0
    var countOk = 0
    var string = ""
    var listSet = listOf(
        "La comunidad del anillo",
        "Pulp Fiction",
        "El señor de los anillos",
        "La lista de Schindler",
        "Batman: El caballero de la noche",
        "El padrino",
        "Sueños de libertad"
    )

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val listDraw = listOf(
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
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        string = getRandomString()
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
        }
        binding.buttonIntento.setOnClickListener {
            var letra: Char = binding.casilleroLetra.text.toString().get(0)
            var result: String = replaceCheckLetter(string, letra)
            binding.textView.text = result
            println("mis vidas" + lives)
            binding.imagenJugador.setImageResource(listDraw[lives])

        }
        println("mis vidas" + lives)
        binding.imagenJugador.setImageResource(listDraw[lives])



        binding.textView.text = replaceLetter(string)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getRandomString(): String {
        return listSet.random()
    }

    fun replaceLetter(str: String): String {
        val chars: CharArray = str.toCharArray()
        for (i in chars.indices) {
            if (chars[i] != ' ') {
                chars[i] = '_'
            }
        }
        return chars.concatToString()
    }

    fun replaceCheckLetter(str: String, myChar: Char): String {
        val chars: CharArray = str.toCharArray()

        if (!chars.contains(myChar.uppercaseChar()) && !chars.contains(myChar.lowercaseChar())) {
            lives++


            Toast.makeText(activity, "message..." + lives, Toast.LENGTH_SHORT).show()


            println("Sorry you failed $lives times.")
        } else {
            if (!charsExist.contains(myChar)) {
                charsExist.add(myChar)
                countOk++
                println("Congratulations on your hit number: $countOk")
            }
        }
        for (i in chars.indices) {
            if (chars[i] != ' ' && chars[i] != myChar.lowercaseChar() && chars[i] != myChar.uppercaseChar()) {
                if (!charsExist.contains(chars[i])) {
                    chars[i] = '_'
                }
            }
        }
        return chars.concatToString()
    }
}