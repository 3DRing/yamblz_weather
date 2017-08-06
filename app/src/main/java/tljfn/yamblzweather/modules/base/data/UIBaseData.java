package tljfn.yamblzweather.modules.base.data;

/**
 * Created by ringov on 31.07.17.
 */

public class UIBaseData implements UIState {

    String error;
    boolean loading;

    protected UIBaseData() {
        error = "";
        loading = false;
    }

    @Override
    public boolean hasError() {
        return error != null && !error.isEmpty();
    }

    @Override
    public String getErrorMessage() {
        return error;
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

    protected static abstract class Builder<D extends UIBaseData, B extends Builder> {

        protected D data;

        public Builder() {
            data = init();
        }

        protected abstract D init();

        protected abstract B getThis();

        public B error(String errorMessage) {
            data.error = errorMessage;
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
