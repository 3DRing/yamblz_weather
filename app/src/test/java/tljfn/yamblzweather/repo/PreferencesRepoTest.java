package tljfn.yamblzweather.repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import tljfn.yamblzweather.BaseFields;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 27.07.17.
 */
public class PreferencesRepoTest {

    SharedPreferences sp;
    Context context;
    PreferencesRepo repo;
    SharedPreferences.Editor editor;

    @Before
    public void setup() {
        sp = mock(SharedPreferences.class);
        context = mock(Context.class);
        editor = mock(SharedPreferences.Editor.class);
        when(context.getSharedPreferences(BaseFields.PREFERENCES_NAME, 0)).thenReturn(sp);
        when(sp.edit()).thenReturn(editor);

        repo = new PreferencesRepo(context);
    }

    @Test
    public void getInterval() throws Exception {
        when(sp.getInt(PreferencesRepo.KEY_INTERVAL, 60)).thenReturn(15);

        repo.getInterval().test()
                .assertNoErrors()
                .assertValue(15);
    }

    @Test
    public void setInterval() throws Exception {
        when(editor.putInt(PreferencesRepo.KEY_INTERVAL, 40)).thenReturn(editor);
        when(sp.getInt(PreferencesRepo.KEY_INTERVAL, 60)).thenReturn(40);

        repo.setInterval(40).test()
                .assertNoErrors()
                .assertComplete();

        verify(editor).putInt(PreferencesRepo.KEY_INTERVAL, 40);

        repo.getInterval().test()
                .assertNoErrors()
                .assertValue(40);
    }

    @Test
    public void updateCurrentCity() throws Exception {
        when(editor.putLong(PreferencesRepo.KEY_CURRENT_CITY, 20L))
                .thenReturn(editor);

        repo.updateCurrentCity(20L).test()
                .assertNoErrors()
                .assertComplete();

        verify(editor).putLong(PreferencesRepo.KEY_CURRENT_CITY, 20L);

        when(sp.getLong(PreferencesRepo.KEY_CURRENT_CITY, PreferencesRepo.DEFAULT_CITY))
                .thenReturn(20L);

        repo.getCurrentCity().test()
                .assertNoErrors()
                .assertValue(20L);
    }

    @Test
    public void getCurrentCity() throws Exception {
        when(sp.getLong(PreferencesRepo.KEY_CURRENT_CITY, PreferencesRepo.DEFAULT_CITY)).thenReturn(PreferencesRepo.DEFAULT_CITY);

        repo.getCurrentCity().test()
                .assertNoErrors()
                .assertValue(PreferencesRepo.DEFAULT_CITY);
    }

}