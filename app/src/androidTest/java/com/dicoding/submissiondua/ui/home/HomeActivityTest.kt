package com.dicoding.submissiondua.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.submissiondua.R.id
import com.dicoding.submissiondua.utils.DummyData
import com.dicoding.submissiondua.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val moviesDummyData = DummyData.getMovies()
    private val tvShowsDummyData = DummyData.getTvShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun tabMovies() {
        onView(withId(id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(moviesDummyData.size))
        onView(withId(id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(id.img_backdrop)).check(matches(isDisplayed()))

        onView(withId(id.img_poster)).check(matches(isDisplayed()))

        onView(withId(id.tv_title)).check(matches(isDisplayed()))
        onView(withId(id.tv_title)).check(matches(withText(moviesDummyData[0].title)))

        onView(withId(id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(id.tv_rating)).check(matches(withText(moviesDummyData[0].rating.toString())))

        onView(withId(id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(id.tv_release_date)).check(matches(withText(moviesDummyData[0].releaseDate)))

        onView(withId(id.tv_genre)).check(matches(isDisplayed()))

        onView(withId(id.tv_synopsis)).check(matches(isDisplayed()))
    }

    @Test
    fun tabTvShows() {
        onView(withText("TV Shows")).perform(click())

        onView(withId(id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShowsDummyData.size))
        onView(withId(id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(id.img_poster)).check(matches(isDisplayed()))

        onView(withId(id.tv_title)).check(matches(isDisplayed()))
        onView(withId(id.tv_title)).check(matches(withText(tvShowsDummyData[0].title)))

        onView(withId(id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(id.tv_rating)).check(matches(withText(tvShowsDummyData[0].rating.toString())))

        onView(withId(id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(id.tv_release_date)).check(matches(withText(tvShowsDummyData[0].releaseDate)))

        onView(withId(id.tv_genre)).check(matches(isDisplayed()))

        onView(withId(id.tv_synopsis)).check(matches(isDisplayed()))
    }

}