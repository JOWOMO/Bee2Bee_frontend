package de.wirvsvirus.abee.data

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

object APIManager {
    private val apiService: APIService

    init {
        val builder = OkHttpClient().newBuilder()
        builder.addInterceptor { chain ->
            chain.withConnectTimeout(100, TimeUnit.SECONDS)
            chain.withReadTimeout(100, TimeUnit.SECONDS)
            val request = chain
                .request()
                .newBuilder()
                .addHeader("x-api-key", "TCxC0rzXPu9vF8suQgPpa36T4lj2X84dWxt61Jb3")
                .addHeader("Content-Type", "application/json; charset=urf-8")
                .build()
            chain.proceed(request)
        }
        val client = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ggsplcsbyc.execute-api.eu-central-1.amazonaws.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(APIService::class.java)
    }

    interface OnBee2BeeResponseCallback<T> {
        fun onResponse(data: T)
        fun onError()
    }

    interface OnBee2BeeVoidResponseCallback {
        fun onResponse()
        fun onError()
    }

    interface APIService {
        // User
        @POST("/default/user")
        fun postUser(@Body body: User): Call<Void>

        @GET("/default/user/{userId}")
        fun getUser(@Path("userId") userId: Int): Call<User>

        @POST("/default/user/login")
        fun loginUser(@Body body: HashMap<String, String>): Call<User>

        @POST("/default/user/assign/{companyId}")
        fun assignUserToCompany(@Path("companyId") companyId: Int): Call<Void>

        // Company
        @POST("/default/company")
        fun postCompany(@Body body: Company): Call<Void>

        @GET("/default/company/{companyId}")
        fun getCompany(@Path("companyId") companyId: Int): Call<Company>

        // Posting
        @POST("/default/posting")
        fun postPosting(@Body body: Posting): Call<Void>

        @GET("/default/posting/{postingId}")
        fun getPosting(@Path("postingId") postingId: Int): Call<Posting>

        @GET("/default/posting/{type}/{longitude}/{latitude}/{radius}")
        fun getPostings(@Path("type") type: Int,
                        @Path("longitude") longitude: String,
                        @Path("latitude") latitude: String,
                        @Path("radius") radius: Int): Call<ArrayList<Posting>>
    }

    // User
    fun postUser(user: User, onResponseCallback: OnBee2BeeVoidResponseCallback) {
        val call = apiService.postUser(user)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() != 200 || response.body() == null) {
                    onResponseCallback.onError()
                    return
                }
                onResponseCallback.onResponse()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResponseCallback.onError()
            }
        })
    }

    fun loginUser(data: HashMap<String, String>, onResponseCallback: OnBee2BeeResponseCallback<User>) {
        val call = apiService.loginUser(data)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() != 200 || response.body() == null) {
                    onResponseCallback.onError()
                    return
                }
                val user = response.body()
                onResponseCallback.onResponse(user!!)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onResponseCallback.onError()
            }
        })
    }

    fun getUser(userId: Int, onResponseCallback: OnBee2BeeResponseCallback<User>) {
        val call = apiService.getUser(userId)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() != 200 || response.body() == null) {
                    onResponseCallback.onError()
                    return
                }

                val user = response.body()
                onResponseCallback.onResponse(user!!)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onResponseCallback.onError()
            }
        })
    }

    fun assignUserToCompany(companyId: Int, onResponseCallback: OnBee2BeeVoidResponseCallback) {
        val call = apiService.assignUserToCompany(companyId)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() != 200 || response.body() == null) {
                    onResponseCallback.onError()
                    return
                }

                onResponseCallback.onResponse()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResponseCallback.onError()
            }
        })
    }

    // Company
    fun postCompany(company: Company, onResponseCallback: OnBee2BeeVoidResponseCallback) {
        val call = apiService.postCompany(company)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() != 200 || response.body() == null) {
                    onResponseCallback.onError()
                    return
                }
                onResponseCallback.onResponse()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResponseCallback.onError()
            }
        })
    }

    fun getCompany(companyId: Int, onResponseCallback: OnBee2BeeResponseCallback<Company>) {
        val call = apiService.getCompany(companyId)

        call.enqueue(object : Callback<Company> {
            override fun onResponse(call: Call<Company>, response: Response<Company>) {
                if (response.code() != 200 || response.body() == null) {
                    onResponseCallback.onError()
                    return
                }

                val company = response.body()
                onResponseCallback.onResponse(company!!)
            }

            override fun onFailure(call: Call<Company>, t: Throwable) {
                onResponseCallback.onError()
            }
        })
    }

    // Posting
    fun postPosting(posting: Posting, onResponseCallback: OnBee2BeeVoidResponseCallback) {
        val call = apiService.postPosting(posting)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() != 200 || response.body() == null) {
                    onResponseCallback.onError()
                    return
                }
                onResponseCallback.onResponse()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResponseCallback.onError()
            }
        })
    }

    fun getPosting(postingId: Int, onResponseCallback: OnBee2BeeResponseCallback<Posting>) {
        val call = apiService.getPosting(postingId)

        call.enqueue(object : Callback<Posting> {
            override fun onResponse(call: Call<Posting>, response: Response<Posting>) {
                if (response.code() != 200 || response.body() == null) {
                    onResponseCallback.onError()
                    return
                }

                val posting = response.body()
                onResponseCallback.onResponse(posting!!)
            }

            override fun onFailure(call: Call<Posting>, t: Throwable) {
                onResponseCallback.onError()
            }
        })
    }

    fun getPostings(type: Int, longitude: String, latitude: String, radius: Int, onResponseCallback: OnBee2BeeResponseCallback<ArrayList<Posting>>) {
        val call = apiService.getPostings(type, longitude, latitude, radius)

        call.enqueue(object : Callback<ArrayList<Posting>> {
            override fun onResponse(call: Call<ArrayList<Posting>>, response: Response<ArrayList<Posting>>) {
                if (response.code() != 200 || response.body() == null) {
                    onResponseCallback.onError()
                    return
                }

                val postings = response.body()
                onResponseCallback.onResponse(postings!!)
            }

            override fun onFailure(call: Call<ArrayList<Posting>>, t: Throwable) {
                onResponseCallback.onError()
            }
        })
    }
}