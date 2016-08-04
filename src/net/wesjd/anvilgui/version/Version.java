package net.wesjd.anvilgui.version;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import net.wesjd.anvilgui.version.impl.Wrapper1_10_R1;
import net.wesjd.anvilgui.version.impl.Wrapper1_8_R3;
import net.wesjd.anvilgui.version.impl.Wrapper1_9_R2;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public enum Version {

	ONE_EIGHT_R3("1_8_R3", Wrapper1_8_R3.class), ONE_NINE_R2("1_9_R2", Wrapper1_9_R2.class), ONE_TEN_R1("1_10_R1", Wrapper1_10_R1.class);

	private static final LoadingCache<Class<? extends VersionWrapper>, VersionWrapper> WRAPPER_CACHE = CacheBuilder.newBuilder().maximumSize(values().length).expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<Class<? extends VersionWrapper>, VersionWrapper>() {
		@Override
		public VersionWrapper load(Class<? extends VersionWrapper> aClass) throws Exception{
			return aClass.newInstance();
		}
	});

	private final String pkg;
	private final Class<? extends VersionWrapper> wrapper;

	Version(String pkg, Class<? extends VersionWrapper> wrapper){
		this.pkg = pkg;
		this.wrapper = wrapper;
	}

	public String getPkg(){
		return pkg;
	}

	public VersionWrapper getWrapper(){
		try {
			return WRAPPER_CACHE.get(wrapper);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public static Version of(final String pkg){
		for (Version version : values())
			if (pkg.equals("v" + version.getPkg()))
				return version;
		return null;
	}

}
