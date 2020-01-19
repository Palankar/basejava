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
        int contained = getContained(resume.getUuid());
        if (contained != -1) {
            storage[contained] = resume;
        } else {
            System.out.println("Resume is missing from storage");
        }
    }

    public void save(Resume resume) {
        if (getContained(resume.getUuid()) != -1) {
            System.out.println("Resume " + resume.getUuid() + " is already in storage");
        } else if (size == storage.length) {
            System.out.println("Resume storage is full");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int contained = getContained(uuid);
        if (contained != -1) {
            return storage[contained];
        } else {
            System.out.println("Resume " + uuid + " is missing from storage");
        }
        return null;
    }

    public void delete(String uuid) {
        int contained = getContained(uuid);
        if (contained != -1) {
            size--;
            System.arraycopy(storage, contained + 1, storage, contained, size);
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

    private int getContained(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
