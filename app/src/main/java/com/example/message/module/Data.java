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
import java.util.List;

public class Data<T> {

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
    public List<T> getDataList(@NonNull String target, @NonNull Class<T> cls) {
        ListData<T> listData = new ListData<>();
        refDb.child(target).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.add(object, snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.update(object, snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listData.getObjects();
    }

    @RequiresApi
    public List<T> getDataList(@NonNull String target, @NonNull Class<T> cls, @NonNull CallBackDataList callBackDataList) {
        ListData<T> listData = new ListData<>();
        refDb.child(target).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.add(object, snapshot.getKey());
                        callBackDataList.add(snapshot);
                        callBackDataList.add();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.update(object, snapshot.getKey());
                        callBackDataList.change(snapshot);
                        callBackDataList.change();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                    callBackDataList.remove(snapshot);
                    callBackDataList.remove();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                    callBackDataList.move(snapshot);
                    callBackDataList.move();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    callBackDataList.cancel(error);
                    callBackDataList.cancel();
                }
            }
        });
        return listData.getObjects();
    }

    @RequiresApi
    public List<T> getDataList(@NonNull String target, @NonNull Class<T> cls, @NonNull AdapterCallBackDataList adapterCallBackDataList) {
        ListData<T> listData = new ListData<>();
        refDb.child(target).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.add(object, snapshot.getKey());
                        adapterCallBackDataList.add(snapshot);
                        adapterCallBackDataList.add();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.update(object, snapshot.getKey());
                        adapterCallBackDataList.change(snapshot);
                        adapterCallBackDataList.change();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                    adapterCallBackDataList.remove(snapshot);
                    adapterCallBackDataList.remove();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                    adapterCallBackDataList.move(snapshot);
                    adapterCallBackDataList.move();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    adapterCallBackDataList.cancel(error);
                    adapterCallBackDataList.cancel();
                }
            }
        });
        return listData.getObjects();
    }

    public List<T> getDataList(@NonNull Class<T> cls) {
        ListData<T> listData = new ListData<>();
        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.add(object, snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.update(object, snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listData.getObjects();
    }

    public List<T> getDataList(@NonNull Class<T> cls, @NonNull CallBackDataList callBackDataList) {
        ListData<T> listData = new ListData<>();
        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.add(object, snapshot.getKey());
                        callBackDataList.add(snapshot);
                        callBackDataList.add();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.update(object, snapshot.getKey());
                        callBackDataList.change(snapshot);
                        callBackDataList.change();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                    callBackDataList.remove(snapshot);
                    callBackDataList.remove();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                    callBackDataList.move(snapshot);
                    callBackDataList.move();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    callBackDataList.cancel(error);
                    callBackDataList.cancel();
                }
            }
        });
        return listData.getObjects();
    }

    public List<T> getDataList(@NonNull Class<T> cls, @NonNull AdapterCallBackDataList adapterCallBackDataList) {
        ListData<T> listData = new ListData<>();
        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.add(object, snapshot.getKey());
                        adapterCallBackDataList.add(snapshot);
                        adapterCallBackDataList.add();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        listData.update(object, snapshot.getKey());
                        adapterCallBackDataList.change(snapshot);
                        adapterCallBackDataList.change();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                    adapterCallBackDataList.remove(snapshot);
                    adapterCallBackDataList.remove();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    listData.delete(snapshot.getKey());
                    adapterCallBackDataList.move(snapshot);
                    adapterCallBackDataList.move();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    adapterCallBackDataList.cancel(error);
                    adapterCallBackDataList.cancel();
                }
            }
        });
        return listData.getObjects();
    }

    @RequiresApi
    public ArrayList<T> getDataArrayList(@NonNull String target, @NonNull Class<T> cls) {
        ArrayListData<T> arrayListData = new ArrayListData<>();
        refDb.child(target).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.add(object, snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.update(object, snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return arrayListData.getObjects();

    }

    @RequiresApi
    public ArrayList<T> getDataArrayList(@NonNull String target, @NonNull Class<T> cls, @NonNull CallBackDataList callBackDataList) {
        ArrayListData<T> arrayListData = new ArrayListData<>();
        refDb.child(target).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.add(object, snapshot.getKey());
                        callBackDataList.add(snapshot);
                        callBackDataList.add();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.update(object, snapshot.getKey());
                        callBackDataList.change(snapshot);
                        callBackDataList.change();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                    callBackDataList.remove(snapshot);
                    callBackDataList.remove();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                    callBackDataList.move(snapshot);
                    callBackDataList.move();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    callBackDataList.cancel(error);
                    callBackDataList.cancel();
                }
            }
        });
        return arrayListData.getObjects();
    }

    @RequiresApi
    public ArrayList<T> getDataArrayList(@NonNull String target, @NonNull Class<T> cls, @NonNull AdapterCallBackDataList adapterCallBackDataList) {
        ArrayListData<T> arrayListData = new ArrayListData<>();
        refDb.child(target).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.add(object, snapshot.getKey());
                        adapterCallBackDataList.add(snapshot);
                        adapterCallBackDataList.add();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.update(object, snapshot.getKey());
                        adapterCallBackDataList.change(snapshot);
                        adapterCallBackDataList.change();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                    adapterCallBackDataList.remove(snapshot);
                    adapterCallBackDataList.remove();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                    adapterCallBackDataList.move(snapshot);
                    adapterCallBackDataList.move();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    adapterCallBackDataList.cancel(error);
                    adapterCallBackDataList.cancel();
                }
            }
        });
        return arrayListData.getObjects();
    }


    public ArrayList<T> getDataArrayList(@NonNull Class<T> cls, @NonNull CallBackDataList callBackDataList) {
        ArrayListData<T> arrayListData = new ArrayListData<>();
        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.add(object, snapshot.getKey());
                        callBackDataList.add(snapshot);
                        callBackDataList.add();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.update(object, snapshot.getKey());
                        callBackDataList.change(snapshot);
                        callBackDataList.change();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                    callBackDataList.remove(snapshot);
                    callBackDataList.remove();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                    callBackDataList.move(snapshot);
                    callBackDataList.move();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    callBackDataList.cancel(error);
                    callBackDataList.cancel();
                }
            }
        });
        return arrayListData.getObjects();
    }

    public ArrayList<T> getDataArrayList(@NonNull Class<T> cls, @NonNull AdapterCallBackDataList adapterCallBackDataList) {
        ArrayListData<T> arrayListData = new ArrayListData<>();
        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.add(object, snapshot.getKey());
                        adapterCallBackDataList.add(snapshot);
                        adapterCallBackDataList.add();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.update(object, snapshot.getKey());
                        adapterCallBackDataList.change(snapshot);
                        adapterCallBackDataList.change();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                    adapterCallBackDataList.remove(snapshot);
                    adapterCallBackDataList.remove();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                    adapterCallBackDataList.move(snapshot);
                    adapterCallBackDataList.move();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    adapterCallBackDataList.cancel(error);
                    adapterCallBackDataList.cancel();
                }
            }
        });
        return arrayListData.getObjects();
    }


    public ArrayList<T> getDataArrayList(@NonNull Class<T> cls) {
        ArrayListData<T> arrayListData = new ArrayListData<>();
        refDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.add(object, snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    T object = snapshot.getValue(cls);
                    if (object != null) {
                        arrayListData.update(object, snapshot.getKey());
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue() != null) {
                    arrayListData.delete(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return arrayListData.getObjects();

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
    public T getDate(@NonNull String target, @NonNull Class<T> cls, CallBackData callBackData) {

        final T[] result = (T[]) new Object[]{null};

        refDb.child(target).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    result[0] = snapshot.getValue(cls);
                    callBackData.change(snapshot);
                    callBackData.change();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    callBackData.cancel(error);
                    callBackData.cancel();
                }
            }
        });

        return result[0];
    }

    @RequiresApi
    public T getDate(@NonNull String target, @NonNull Class<T> cls, AdapterCallBackData adapterCallBackData) {

        final T[] result = (T[]) new Object[]{null};

        refDb.child(target).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    result[0] = snapshot.getValue(cls);
                    adapterCallBackData.change(snapshot);
                    adapterCallBackData.change();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    adapterCallBackData.cancel(error);
                    adapterCallBackData.cancel();
                }
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


    public T getDate(@NonNull Class<T> cls, @NonNull CallBackData callBackData) {

        final T[] result = (T[]) new Object[]{null};

        refDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    result[0] = snapshot.getValue(cls);
                    callBackData.change();
                    callBackData.change(snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    callBackData.cancel();
                    callBackData.cancel(error);
                }
            }
        });

        return result[0];
    }

    public T getDate(@NonNull Class<T> cls, @NonNull AdapterCallBackData adapterCallBackData) {

        final T[] result = (T[]) new Object[]{null};

        refDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    result[0] = snapshot.getValue(cls);
                    adapterCallBackData.change();
                    adapterCallBackData.change(snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error != null) {
                    adapterCallBackData.cancel();
                    adapterCallBackData.cancel(error);
                }
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
                if (task.isSuccessful()) {
                    callBackQuery.isSuccess(task);
                    callBackQuery.isSuccess();
                } else {
                    callBackQuery.isFail(task);
                    callBackQuery.isFail();
                }
            }
        });
    }

    @RequiresApi
    public void add(@NonNull String target, @NonNull T object, @NonNull AdapterCallBackQuery adapterCallBackQuery) {
        refDb.child(target).setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    adapterCallBackQuery.isSuccess(task);
                    adapterCallBackQuery.isSuccess();
                } else {
                    adapterCallBackQuery.isFail(task);
                    adapterCallBackQuery.isFail();
                }
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
                if (task.isSuccessful()) {
                    callBackQuery.isSuccess(task);
                    callBackQuery.isSuccess();
                } else {
                    callBackQuery.isFail(task);
                    callBackQuery.isFail();
                }
            }
        });
    }

    public void add(@NonNull T object, @NonNull AdapterCallBackQuery adapterCallBackQuery) {
        refDb.push().setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    adapterCallBackQuery.isSuccess(task);
                    adapterCallBackQuery.isSuccess();
                } else {
                    adapterCallBackQuery.isFail(task);
                    adapterCallBackQuery.isFail();
                }
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
                if (task.isSuccessful()) {
                    callBackQuery.isSuccess(task);
                    callBackQuery.isSuccess();
                } else {
                    callBackQuery.isFail(task);
                    callBackQuery.isFail();
                }
            }
        });
    }

    @RequiresApi
    public void update(@NonNull String target, @NonNull T object, @NonNull AdapterCallBackQuery adapterCallBackQuery) {
        refDb.child(target).setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    adapterCallBackQuery.isSuccess(task);
                    adapterCallBackQuery.isSuccess();
                } else {
                    adapterCallBackQuery.isFail(task);
                    adapterCallBackQuery.isFail();
                }
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
                if (task.isSuccessful()) {
                    callBackQuery.isSuccess(task);
                    callBackQuery.isSuccess();
                } else {
                    callBackQuery.isFail(task);
                    callBackQuery.isFail();
                }
            }
        });
    }

    public void update(@NonNull T object, @NonNull AdapterCallBackQuery adapterCallBackQuery) {
        refDb.setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    adapterCallBackQuery.isSuccess(task);
                    adapterCallBackQuery.isSuccess();
                } else {
                    adapterCallBackQuery.isFail(task);
                    adapterCallBackQuery.isFail();
                }
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
                if (task.isSuccessful()) {
                    callBackQuery.isSuccess(task);
                    callBackQuery.isSuccess();
                } else {
                    callBackQuery.isFail(task);
                    callBackQuery.isFail();
                }
            }
        });
    }


    @RequiresApi
    public void delete(@NonNull String target, @NonNull AdapterCallBackQuery adapterCallBackQuery) {
        refDb.child(target).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    adapterCallBackQuery.isSuccess(task);
                    adapterCallBackQuery.isSuccess();
                } else {
                    adapterCallBackQuery.isFail(task);
                    adapterCallBackQuery.isFail();
                }
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
                if (task.isSuccessful()) {
                    callBackQuery.isSuccess(task);
                    callBackQuery.isSuccess();
                } else {
                    callBackQuery.isFail(task);
                    callBackQuery.isFail();
                }
            }
        });
    }

    public void delete(@NonNull AdapterCallBackQuery adapterCallBackQuery) {
        refDb.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    adapterCallBackQuery.isSuccess(task);
                    adapterCallBackQuery.isSuccess();
                } else {
                    adapterCallBackQuery.isFail(task);
                    adapterCallBackQuery.isFail();
                }
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

    public class ListData<T> {
        private final List<T> objects;
        private final List<String> keys;

        public ListData() {
            objects = new ArrayList<>();
            keys = new ArrayList<>();
        }

        public List<T> getObjects() {
            return objects;
        }

        public List<String> getKeys() {
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
