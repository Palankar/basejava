package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume resume) {
        if (resume != null && !(isContained(resume.getUuid()) == -1)) {
            storage[isContained(resume.getUuid())] = resume;
        } else  {
            System.out.println("Resume is missing from storage");
        }
    }

    public void save(Resume r) {
        if (isContained(r.getUuid()) == -1 && size != 10_000) {
            storage[size] = r;
            size++;
        } else if (size == 10_000) {
            System.out.println("Resume storage is full");
        } else {
            System.out.println("Resume " + r.getUuid() + " is already in storage");
        }
    }

    public Resume get(String uuid) {
        Resume resume = null;
        if (!(isContained(uuid) == -1)) {
            resume = storage[isContained(uuid)];
        } else {
            System.out.println("Resume " + uuid + " is missing from storage");
        }
        return resume;
    }

    public void delete(String uuid) {
        if (!(isContained(uuid) == -1)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    size--;
                    System.arraycopy(storage, i + 1, storage, i, size);
                    break;
                }
            }
        } else {
            System.out.println("Resume " + uuid + " is missing from storage");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allRes = new Resume[size];
        System.arraycopy(storage, 0, allRes, 0, size);
        return allRes;
    }

    public int size() {
        return size;
    }

    private int isContained(String uuid) {
        int contains = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                contains = i;
                break;
            }
        }
        return contains;
    }
}
