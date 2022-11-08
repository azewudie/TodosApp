package com.test.todos
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.todos.adapter.TodoAdapter
import com.test.todos.databinding.ActivityMainBinding
import com.test.todos.models.TodosPresentationData
import com.test.todos.models.data.Todos
import com.test.todos.viewModel.TodosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//binding object

    private var binding: ActivityMainBinding? = null

    // adapter object
    private val adapter: TodoAdapter by lazy {
        TodoAdapter(Todos())
    }

    // viewModel object
    private val viewModel: TodosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initView()
        initObservables()


    }
    // initial observable
    private fun initObservables(){
        viewModel.todosDataAPIResult.observe(this){
            processData(it)
        }

    }
    private fun processData(presentationData: TodosPresentationData){
        when(presentationData){
            is TodosPresentationData.Response ->{
                updateAdapter(presentationData.response)
            }
            is TodosPresentationData.Error ->{
                showError(presentationData.errorMessage)
            }
            is TodosPresentationData.Loading ->{
                showLoading(presentationData.isLoading)
            }
        }
    }
    private fun showLoading(loading:Boolean){
        if(loading){
            binding?.rvTodos?.visibility = View.GONE
            binding?.pbLoading?.visibility = View.VISIBLE
        }else{
            binding?.rvTodos?.visibility = View.VISIBLE
            binding?.pbLoading?.visibility = View.GONE
        }
    }
    private fun showError(errorMessage: String){
        binding?.let {
            Snackbar.make(it.root,errorMessage,Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss"){}
                .show()
        }

    }
    private fun updateAdapter(response:Todos){
        Todos().apply {
            addAll(response)
            adapter.todoItem = this
        }
    }
    private fun initView(){
        binding?.rvTodos?.layoutManager = LinearLayoutManager(this)
        binding?.rvTodos?.adapter = adapter

    }
}