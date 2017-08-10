package tljfn.yamblzweather.modules.base.data;

import android.support.annotation.NonNull;

/**
 * Created by ringov on 31.07.17.
 */

public class UIBaseData implements UIState {

    String error;
    boolean loading;
    boolean empty;

    protected UIBaseData() {
        error = "";
        loading = false;
        empty = false;
    }

    public boolean isEmpty() {
        return empty;
    }

    @Override
    public boolean hasError() {
        return error != null && !error.isEmpty();
    }

    @Override
    public String getErrorMessage() {
        return error != null ? error : "";
    }

    @Override
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public boolean isLoading() {
        return loading;
    }

    public void resetErrorState() {
        error = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UIBaseData that = (UIBaseData) o;

        if (isLoading() != that.isLoading()) return false;
        return error != null ? error.equals(that.error) : that.error == null;

    }

    @Override
    public int hashCode() {
        int result = error != null ? error.hashCode() : 0;
        result = 31 * result + (isLoading() ? 1 : 0);
        return result;
    }

    protected static abstract class Builder<D extends UIBaseData, B extends Builder> {

        protected D data;

        public Builder() {
            data = checkedInitialization();
        }

        private D checkedInitialization() {
            D data = init();
            if (data == null) {
                throw new IllegalStateException("You must implement init() method and provide an instance of a data. Appeared in "
                        + this.getClass().getSimpleName());
            }
            return data;
        }

        /**
         * @return the new instance of {@link UIBaseData}-child class
         */
        protected abstract D init();

        /**
         * @return the instance of the exact {@link Builder}-child class
         */
        protected abstract B getThis();

        public B error(@NonNull String errorMessage) {
            data.error = errorMessage;
            return getThis();
        }

        public B empty(boolean empty) {
            this.data.empty = empty;
            return getThis();
        }

        public D build() {
            return data;
        }
    }


    public static class ErrorBuilder extends Builder<UIBaseData, ErrorBuilder> {
        @Override
        protected UIBaseData init() {
            return new UIBaseData();
        }

        @Override
        protected ErrorBuilder getThis() {
            return this;
        }

    }
}
