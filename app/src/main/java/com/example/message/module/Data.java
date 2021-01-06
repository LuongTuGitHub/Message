package com.example.message.module;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Data<T> {

    private final static int ADDED = 1;
    private final static int CHANGED = 2;
    private final static int REMOVED = 3;
    private final static int MOVED = 4;

    private final DatabaseReference refDb;
    private final FirebaseDatabase database;

    public Data() {
        database = FirebaseDatabase.getInstance();
        refDb = database.getReference();
    }

    public Data(@NonNull String target) {
        database = FirebaseDatabase.getInstance();
        refDb = database.getReference(target);
    }

    public Data<T> settingDatabase() {
        database.setPersistenceEnabled(true);
        database.setPersistenceCacheSizeBytes(Long.MAX_VALUE);
        return this;
    }

    public Data<T> settingDatabase(long sizeCache) {
        database.setPersistenceEnabled(true);
        database.setPersistenceCacheSizeBytes(sizeCache);
        return this;
    }

    @RequiresApi
    public Collection<T> getDataList(@NonNull String target, @NonNull Class<T> cls) {
        ArrayListData<T> result = new ArrayListData<>();
        refDb.child(target).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.add(object, snapshot.getKey());
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.update(object, snapshot.getKey());
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return result.getObjects();

    }

    @RequiresApi
    public Collection<T> getDataList(@NonNull String target, @NonNull Class<T> cls, @NonNull DataListListener listener) {
        ArrayListData<T> result = new ArrayListData<>();
        refDb.child(target).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.add(object, snapshot.getKey());
                            listener.onAdded(snapshot);
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.update(object, snapshot.getKey());
                            listener.onChanged(snapshot);
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                        listener.onRemoved(snapshot);
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                        listener.onMoved(snapshot);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onCancelled(error);
            }
        });
        return result.getObjects();
    }

    @RequiresApi
    public Collection<T> getDataList(@NonNull String target, @NonNull Class<T> cls, @NonNull DataChangeListener listener) {
        ArrayListData<T> result = new ArrayListData<>();
        refDb.child(target).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.add(object, snapshot.getKey());
                            listener.onChanged(snapshot, ADDED);
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.update(object, snapshot.getKey());
                            listener.onChanged(snapshot, CHANGED);
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                        listener.onChanged(snapshot, REMOVED);
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                        listener.onChanged(snapshot, MOVED);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onCancelled(error);
            }
        });
        return result.getObjects();
    }

    public Collection<T> getDataList(@NonNull Class<T> cls) {
        ArrayListData<T> result = new ArrayListData<>();
        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.add(object, snapshot.getKey());
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.update(object, snapshot.getKey());
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return result.getObjects();

    }

    public Collection<T> getDataList(@NonNull Class<T> cls, @NonNull DataListListener listener) {
        ArrayListData<T> result = new ArrayListData<>();
        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.add(object, snapshot.getKey());
                            listener.onAdded(snapshot);
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.update(object, Objects.requireNonNull(snapshot.getKey()));
                            listener.onChanged(snapshot);
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                        listener.onRemoved(snapshot);
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                        listener.onMoved(snapshot);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onCancelled(error);
            }
        });
        return result.getObjects();
    }

    public Collection<T> getDataList(@NonNull Class<T> cls, @NonNull DataChangeListener listener) {
        ArrayListData<T> result = new ArrayListData<>();
        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.add(object, snapshot.getKey());
                            listener.onChanged(snapshot, ADDED);
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        if (snapshot.getKey() != null) {
                            result.update(object, Objects.requireNonNull(snapshot.getKey()));
                            listener.onChanged(snapshot, CHANGED);
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                        listener.onChanged(snapshot, REMOVED);
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    if (snapshot.getKey() != null) {
                        result.delete(snapshot.getKey());
                        listener.onChanged(snapshot, MOVED);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onCancelled(error);
            }
        });
        return result.getObjects();
    }

    @RequiresApi
    public T getDate(@NonNull String target, @NonNull Class<T> cls) {

        final T[] result = (T[]) new Object[]{null};

        refDb.child(target).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    result[0] = snapshot.getValue(cls);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return result[0];
    }

    @RequiresApi
    public T getDate(@NonNull String target, @NonNull Class<T> cls, DataListener listener) {

        final T[] result = (T[]) new Object[]{null};

        refDb.child(target).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    result[0] = snapshot.getValue(cls);
                    listener.onChanged(snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onCancelled(error);
            }
        });

        return result[0];
    }

    public T getDate(@NonNull Class<T> cls) {

        final T[] result = (T[]) new Object[]{null};

        refDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    result[0] = snapshot.getValue(cls);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return result[0];
    }

    public T getDate(@NonNull Class<T> cls, @NonNull DataListener listener) {

        final T[] result = (T[]) new Object[]{null};

        refDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    result[0] = snapshot.getValue(cls);
                    listener.onChanged(snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onCancelled(error);
            }
        });

        return result[0];
    }

    @RequiresApi
    public void add(@NonNull String target, @NonNull T object) {
        refDb.child(target).setValue(object);
    }

    @RequiresApi
    public void add(@NonNull String target, @NonNull T object, @NonNull CallBackQuery callBackQuery) {
        refDb.child(target).setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callBackQuery.function(task);
            }
        });
    }

    public void add(@NonNull T object) {
        refDb.push().setValue(object);
    }

    public void add(@NonNull T object, @NonNull CallBackQuery callBackQuery) {
        refDb.push().setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callBackQuery.function(task);
            }
        });
    }

    @RequiresApi
    public void update(@NonNull String target, @NonNull T object) {
        refDb.child(target).setValue(object);
    }

    @RequiresApi
    public void update(@NonNull String target, @NonNull T object, @NonNull CallBackQuery callBackQuery) {
        refDb.child(target).setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callBackQuery.function(task);
            }
        });
    }

    public void update(@NonNull T object) {
        refDb.setValue(object);
    }

    public void update(@NonNull T object, @NonNull CallBackQuery callBackQuery) {
        refDb.setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callBackQuery.function(task);
            }
        });
    }

    @RequiresApi
    public void delete(@NonNull String target) {
        refDb.child(target).removeValue();
    }

    @RequiresApi
    public void delete(@NonNull String target, @NonNull CallBackQuery callBackQuery) {
        refDb.child(target).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callBackQuery.function(task);
            }
        });
    }

    public void delete() {
        refDb.removeValue();
    }

    public void delete(@NonNull CallBackQuery callBackQuery) {
        refDb.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callBackQuery.function(task);
            }
        });
    }

    public static class ArrayListData<T> {

        private final ArrayList<T> objects;
        private final ArrayList<String> keys;

        public ArrayListData() {
            objects = new ArrayList<>();
            keys = new ArrayList<>();
        }

        public ArrayList<T> getObjects() {
            return objects;
        }

        public ArrayList<String> getKeys() {
            return keys;
        }

        public void add(@NonNull T object, @NonNull String key) {
            objects.add(object);
            keys.add(key);
        }

        public void update(@NonNull T object, @NonNull String key) {
            for (String stg : keys) {
                if (stg.equals(key)) {
                    int index = keys.indexOf(stg);
                    objects.set(index, object);
                    break;
                }
            }
        }

        public void delete(@NonNull String key) {
            for (String stg : keys) {
                if (stg.equals(key)) {
                    int index = keys.indexOf(stg);
                    objects.remove(index);
                    keys.remove(index);
                    break;
                }
            }
        }

        public T get(@NonNull String key) {
            for (String stg : keys) {
                if (stg.equals(key)) {
                    int index = keys.indexOf(stg);
                    return objects.get(index);
                }
            }

            return null;
        }
    }
}
