/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tljfn.yamblzweather.di.component;

import android.app.Application;
import android.app.Service;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.di.module.AppModule;
import tljfn.yamblzweather.di.module.MainActivityModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class
})
public interface AppComponent {
    void inject(App app);

    void inject(Service service);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
//        @BindsInstance
//        Builder service(Service service);

        AppComponent build();
    }
}
