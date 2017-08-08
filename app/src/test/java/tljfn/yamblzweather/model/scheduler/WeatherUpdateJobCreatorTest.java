package tljfn.yamblzweather.model.scheduler;

import com.evernote.android.job.Job;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ringov on 07.08.17.
 */
public class WeatherUpdateJobCreatorTest {

    @Test
    public void correct_job_creation() {
        WeatherUpdateJobCreator jobCreator = new WeatherUpdateJobCreator();
        Job job = jobCreator.create(WeatherUpdateJob.TAG);

        assertTrue(job != null);
    }

    @Test
    public void wrong_job_creation() {
        WeatherUpdateJobCreator jobCreator = new WeatherUpdateJobCreator();
        Job job = jobCreator.create("any");

        assertTrue(job == null);
    }
}