package com.example.myapplication.trivia

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.entity.Player
import com.example.myapplication.databinding.FragmentTriviaBinding

class TriviaFragment: Fragment() {
    private var _binding: FragmentTriviaBinding? = null
    //answer Button to insert into the function-functionalityDisplay->
    // the number of answer pressed by the user.
   private lateinit var answerButton:RadioButton
    //use after init:
    private val binding get()=_binding!!

    // questions getter
    private fun getQuestions(): MutableList<String> {
        return mutableListOf(
            "what is the player's first name?",
            "when is it his birthday?", "what is his sport type?"
        )
    }

    // answers getter
    private fun getDisplayAnswers(): MutableList<Answers> {


        return mutableListOf(
            Answers(getPlayer()!!.fullName, " messi", "lebron ", "sanchez"),
            Answers("2000-07-15", "1985-12-13", "1973-03-03", getPlayer()!!.birthday),
            Answers("aerobic Type", "ball Type", getPlayer()!!.sportType, "multi type athlete")
        )
    }
    //rightAnswersGetter
    private fun getRightAnswers(): List<String> {
        return listOf(getDisplayAnswers()[0].A, getDisplayAnswers()[1].D, getDisplayAnswers()[2].C)
    }


    ///// GET THE player obj
    private fun getPlayer():Player?{
        val p = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("player", Player::class.java)
        } else {
            arguments?.getParcelable("player") as Player?
        }
        return p
    }


    /////  GET THE  QUESTION NUMBER ARGUMENT
    private fun getNumberOfQuestion(): Int {
        return arguments?.getInt("number of question")!!.toInt()
    }





    ////////////// displaying questions and answers
     private fun displayQuestionsAndAnswers(i:Int){

        //questions for the user
        val questions : MutableList<String> =getQuestions()


        val displayAnswers: MutableList<Answers> =getDisplayAnswers()
        binding.questioning.text = questions[i]
        binding.A.text=displayAnswers[i].A
        binding.B.text=displayAnswers[i].B
        binding.C.text=displayAnswers[i].C
        binding.D.text=displayAnswers[i].D


    }


    // method for the display logic of the fragment.
    //if answer is right and its not the last question,continue to the next.
    //if answer is wrong->failedFragment will be displayed
    //if user was right at al questions-> winFragment
    //returning i ->the current question number so it would not reset its value to 0.
    private fun displayLogic(a:RadioButton,num:Int):Int{
        var i=num
        println(i)
          if (getRightAnswers()[i] == a.text.toString()&&i <  getQuestions().size-1) {
                i++
                displayQuestionsAndAnswers(i)
            }
            else if (getRightAnswers()[i] != a.text.toString())
                findNavController().navigate(
                    R.id.action_triviaFragment_to_failedFragment,
                    Bundle().apply { putParcelable("player", getPlayer());putInt("number of question",i) }
                )
            else   findNavController().navigate(R.id.action_triviaFragment_to_winFragment)

        return i
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTriviaBinding.inflate(inflater, container, false)
         return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //getting num of question to return to the same one if the user was wrong in a specific question
        val num=getNumberOfQuestion()
        //i=>index for the current number of question we are right now.
        var i=num
        //init display for the first question and answers
        displayQuestionsAndAnswers(i)
        //if the user press on answer a & button answer,activate this block of code
        binding.A.setOnClickListener {
        answerButton=binding.A
            binding.answer.setOnClickListener {
                println(i)
                //saves the current question number
                i=displayLogic(answerButton,i)

            }
        }
        //if the user press on answer b & button answer,activate this block of code
            binding.B.setOnClickListener {
                answerButton=binding.B
                binding.answer.setOnClickListener {
                    //saves the current question number
                    i=displayLogic(answerButton,i)


                }
            }
        //if the user press on answer c & button answer,activate this block of code
            binding.C.setOnClickListener {
                answerButton=binding.C
                binding.answer.setOnClickListener {
                    //saves the current question number
                   i=displayLogic(answerButton,i)

                }
            }
        //if the user press on answer d & button answer,activate this block of code
            binding.D.setOnClickListener {
                answerButton=binding.D
                binding.answer.setOnClickListener {
                    //saves the current question number
                    i=displayLogic(answerButton,i)
                }
            }





    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}