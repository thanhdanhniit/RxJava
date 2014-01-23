/**
 * Copyright 2014 Netflix, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rx.operators;

import static org.mockito.Mockito.*;

import org.junit.Test;

import rx.Observable;
import rx.Observer;
import rx.observers.TestObserver;

public class OperatorCastTest {

    @Test
    public void testCast() {
        Observable<?> source = Observable.from(1, 2);
        Observable<Integer> observable = source.cast(Integer.class);

        @SuppressWarnings("unchecked")
        Observer<Integer> aObserver = mock(Observer.class);
        observable.subscribe(new TestObserver<Integer>(aObserver));
        verify(aObserver, times(1)).onNext(1);
        verify(aObserver, times(1)).onNext(1);
        verify(aObserver, never()).onError(
                org.mockito.Matchers.any(Throwable.class));
        verify(aObserver, times(1)).onCompleted();
    }

    @Test
    public void testCastWithWrongType() {
        Observable<?> source = Observable.from(1, 2);
        Observable<Boolean> observable = source.cast(Boolean.class);

        @SuppressWarnings("unchecked")
        Observer<Boolean> aObserver = mock(Observer.class);
        observable.subscribe(new TestObserver<Boolean>(aObserver));
        verify(aObserver, times(1)).onError(
                org.mockito.Matchers.any(ClassCastException.class));
    }
}
