import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twiliosample.util.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This extension method enqueues the call using the coroutine and
 * return the Result instance with Success or Failure
 */
fun <T : Any> Call<T>.getResult(): LiveData<Resource<T>> {
    val resultLiveData: MutableLiveData<Resource<T>> = MutableLiveData()
    resultLiveData.value = Resource.loading()
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            resultLiveData.value = Resource.error(message = t.message)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                resultLiveData.value = Resource.success(response.body())
            } else {
                resultLiveData.value =
                    Resource.error(message = "Something went wrong, please try again")
            }
        }
    })
    return resultLiveData
}

// show toast message
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

// check internet connectivity
fun Context.isNetworkConnected(): Boolean {
    val cm: ConnectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
}

//show view
fun View.show() {
    this.visibility = View.VISIBLE
}

// hide view
fun View.hide() {
    this.visibility = View.GONE
}

// invisible view
fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

