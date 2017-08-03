package tljfn.yamblzweather.scheduler;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by ringov on 15.07.17.
 */

public class WeatherUpdateJobCreator implements JobCreator {
    @Override
    public Job create(String tag) {
        return tag.equals(WeatherUpdateJob.TAG) ? new WeatherUpdateJob() : null;
    }
}
