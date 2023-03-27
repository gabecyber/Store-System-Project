using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy1Patterns : MonoBehaviour {

    private Rigidbody2D rb;
    private Transform currentPoint;
    public float speed = 0f;
    // Start is called before the first frame update
    void Start() {
        rb = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update() {
        rb.velocity = new Vector2(0, -15f);
    }


}
