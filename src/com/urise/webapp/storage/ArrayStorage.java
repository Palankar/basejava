package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume resume) {
        if (resume != null && isContained(resume.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    storage[i] = resume;
                    break;
                }
            }
        } else  {
            System.out.println("Resume is missing from storage");
        }
    }

    public void save(Resume r) {
        if (!isContained(r.getUuid())) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Resume " + r.getUuid() + " is already in storage");
        }
    }

    public Resume get(String uuid) {
        Resume resume = null;
        if (isContained(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    resume = storage[i];
                    break;
                }
            }
        } else {
            System.out.println("Resume " + uuid + " is missing from storage");
        }
        return resume;
    }

    public void delete(String uuid) {
        if (isContained(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    size--;
                    System.arraycopy(storage, i + 1, storage, i, size - i);
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
        for (int i = 0; i < size; i++) {
            allRes[i] = storage[i];
        }
        return allRes;
    }

    public int size() {
        return size;
    }

    private boolean isContained(String uuid) {
        boolean contains = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
